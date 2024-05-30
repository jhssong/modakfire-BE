package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Donation;
import com.walkak.modakfire.domain.Item;
import com.walkak.modakfire.dto.*;
import com.walkak.modakfire.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final CenterService centerService;
    private final ItemService itemService;
    private final MemberService memberService;

    public FastDonationResponseDTO createFastDonation(FastDonationRequestDTO fastDonationRequestDTO) {

        FastDonationResponseDTO donationResponseDTO = new FastDonationResponseDTO();

        // Create random orderId
        long orderId = Math.abs(UUID.randomUUID().getMostSignificantBits());

        // Find centers to donate
        CenterRequestDTO centerRequestDTO = new CenterRequestDTO(
                fastDonationRequestDTO.getCity(),
                fastDonationRequestDTO.getGu(),
                fastDonationRequestDTO.getCenterType());
        List<CenterResponseDTO> centerList = centerService.findCentersByCon(centerRequestDTO);

        Long totalAmount = fastDonationRequestDTO.getTotalAmount();
        while(totalAmount != 0L) {
            // Find items at random center
            Random rand = new Random();
            CenterResponseDTO randomCenter = centerList.get(rand.nextInt(centerList.size()));
            System.out.println(randomCenter);

            // Get ItemList from center
            List<Item> itemList = itemService.findItemsByCenterId(randomCenter.getId());

            // Update item status (update the raisedAmount)
            for (int i = 0; i < itemList.size(); i++) {
                Item item = itemList.get(i);
                Long price = item.getPrice();
                Long raisedAmount = item.getRaisedAmount();
                long donatedAmount;

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
                donationResponseDTO.addDonationResponseDTO(donation.translate());

                if (totalAmount == 0L || i == itemList.size() - 1) break;
            }
        }

        return donationResponseDTO;
    }
}
