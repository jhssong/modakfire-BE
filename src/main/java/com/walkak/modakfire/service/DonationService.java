package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.Donation;
import com.walkak.modakfire.domain.Item;
import com.walkak.modakfire.dto.*;
import com.walkak.modakfire.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final CenterService centerService;
    private final ItemService itemService;
    private final MemberService memberService;

    public DonationResponseDTO createFastDonation(FastDonationRequestDTO fastDonationRequestDTO, Long orderId) {

        DonationResponseDTO donationResponseDTO = new DonationResponseDTO();

        // Create random orderId if null
        if (orderId == null) {
            orderId = Math.abs(UUID.randomUUID().getMostSignificantBits());
        }

        // Find centers to donate
        CenterRequestDTO centerRequestDTO = new CenterRequestDTO(
                fastDonationRequestDTO.getCity(),
                "전체",
                fastDonationRequestDTO.getCenterType());
        List<CenterResponseDTO> tempCenterList = centerService.findCentersByCon(centerRequestDTO);
        ArrayList<CenterResponseDTO> centerList = new ArrayList<>(tempCenterList);
        Collections.shuffle(centerList);

        Long totalAmount = fastDonationRequestDTO.getTotalAmount();

        // Assume that totalDonation doesn't exceed total amount of items of centers in the city
        for (int centerI = 0; centerI < centerList.size(); centerI++) {
            // Get ItemList from center
            List<Item> itemList = itemService.findItemsByCenterId(centerList.get(centerI).getId());

            // Update item status (update the raisedAmount)
            for (int i = 0; i < itemList.size(); i++) {
                Item item = itemList.get(i);
                Long price = item.getPrice();
                Long raisedAmount = item.getRaisedAmount();
                long donatedAmount = 0;

                if (Objects.equals(price, raisedAmount)) continue;

                if (raisedAmount + totalAmount <= price) {  // totalAmount <= needed
                    raisedAmount += totalAmount;
                    donatedAmount = totalAmount;
                    totalAmount = 0L;

                } else {  // totalAmount > needed
                    totalAmount -= (price - raisedAmount);
                    donatedAmount = price - raisedAmount;
                    raisedAmount = price;
                }

                item.setRaisedAmount(raisedAmount);
                itemService.updateItemByDonation(item);

                // Create and save a new Donation entity
                Donation donation = Donation.builder()
                        .date(LocalDateTime.now())
                        .totalAmount(donatedAmount)
                        .orderId(orderId)
                        .item(item)
                        .member(memberService.getMemberEntityById(fastDonationRequestDTO.getUserId()))
                        .build();

                donationRepository.save(donation);
                donationResponseDTO.add(donation.translate());

                if (totalAmount == 0) return donationResponseDTO;
            }
        }

        return donationResponseDTO;
    }

    public  DonationResponseDTO createDonation(DonationRequestDTO donationRequestDTO) {
        DonationResponseDTO donationResponseDTO = new DonationResponseDTO();

        // Create random orderId
        Long orderId = Math.abs(UUID.randomUUID().getMostSignificantBits());

        Long totalAmount = donationRequestDTO.getTotalAmount();

        Item item = itemService.getItemEntityById(donationRequestDTO.getItemId());
        Long price = item.getPrice();
        Long raisedAmount = item.getRaisedAmount();
        Long donatedAmount;

        if (raisedAmount + totalAmount <= price) {  // totalAmount <= needed
            raisedAmount += totalAmount;
            donatedAmount = totalAmount;
            totalAmount = 0L;

        } else {  // totalAmount > needed
            totalAmount -= (price - raisedAmount);
            donatedAmount = price - raisedAmount;
            raisedAmount = price;
        }

        item.setRaisedAmount(raisedAmount);
        itemService.updateItemByDonation(item);

        // Create and save a new Donation entity
        Donation donation = Donation.builder()
                .date(LocalDateTime.now())
                .totalAmount(donatedAmount)
                .orderId(orderId)
                .item(item)
                .member(memberService.getMemberEntityById(donationRequestDTO.getMemberId()))
                .build();

        donationRepository.save(donation);
        donationResponseDTO.add(donation.translate());

        // if totalAmount was left
        if (totalAmount != 0) {
            // Get Center entity
            Center center = item.getCenter();

            FastDonationRequestDTO fastDonationRequestDTO = FastDonationRequestDTO.builder()
                    .userId(donationRequestDTO.getMemberId())
                    .totalAmount(totalAmount)
                    .city(center.getCity())
                    .centerType(center.getCenterType())
                    .build();

            DonationResponseDTO fastDonationResult = createFastDonation(fastDonationRequestDTO, orderId);
            for (SimpleDonationResponseDTO s: fastDonationResult.getDonationResponseDTOList()) {
                donationResponseDTO.add(s);
            }
        }

        return donationResponseDTO;
    }
}
