package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.Donation;
import com.walkak.modakfire.domain.Item;
import com.walkak.modakfire.domain.Market;
import com.walkak.modakfire.dto.*;
import com.walkak.modakfire.repository.CenterRepository;
import com.walkak.modakfire.repository.DonationRepository;
import com.walkak.modakfire.repository.ItemRepository;
import com.walkak.modakfire.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DonationService {

    private final DonationRepository donationRepository;
    private final CenterService centerService;
    private final ItemService itemService;
    private final MemberService memberService;
    private final CenterRepository centerRepository;
    private final ItemRepository itemRepository;
    private final MarketRepository marketRepository;

    @Transactional
    public FastDonationCreateResponseDTO createFastDonation(FastDonationCreateRequestDTO fastDonationCreateRequestDTO, Long orderId) {

        FastDonationCreateResponseDTO fastDonationCreateResponseDTO = new FastDonationCreateResponseDTO();

        // Create random orderId if null
        if (orderId == null) {
            orderId = Math.abs(UUID.randomUUID().getMostSignificantBits());
        }

        // Find centers to donate
        CenterRequestDTO centerRequestDTO = new CenterRequestDTO(
                fastDonationCreateRequestDTO.getCity(),
                "전체",
                fastDonationCreateRequestDTO.getCenterType());
        List<CenterResponseDTO> tempCenterList = centerService.findCentersByCon(centerRequestDTO);
        ArrayList<CenterResponseDTO> centerList = new ArrayList<>(tempCenterList);
        Collections.shuffle(centerList);

        Long totalAmount = fastDonationCreateRequestDTO.getTotalAmount();

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
                        .member(memberService.getMemberEntityById(fastDonationCreateRequestDTO.getMemberId()))
                        .build();

                donationRepository.save(donation);
                fastDonationCreateResponseDTO.add(donation.translate());

                if (totalAmount == 0) return fastDonationCreateResponseDTO;
            }
        }

        if (totalAmount != 0) {
            Center center = centerRepository.findById(centerList.get(0).getId()).orElseThrow();
            center.setBalance(totalAmount);
            centerRepository.save(center);
        }
        return fastDonationCreateResponseDTO;
    }

    @Transactional
    public  DonationCreateResponseDTO createDonation(DonationCreateRequestDTO donationRequestDTO) {
        DonationCreateResponseDTO donationResponseDTO = new DonationCreateResponseDTO();

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

            FastDonationCreateRequestDTO fastDonationCreateRequestDTO = FastDonationCreateRequestDTO.builder()
                    .memberId(donationRequestDTO.getMemberId())
                    .totalAmount(totalAmount)
                    .city(center.getCity())
                    .centerType(center.getCenterType())
                    .build();

            FastDonationCreateResponseDTO fastDonationResult = createFastDonation(fastDonationCreateRequestDTO, orderId);
            for (SimpleDonationResponseDTO s: fastDonationResult.getDonationResponseDTOList()) {
                donationResponseDTO.add(s);
            }
        }

        return donationResponseDTO;
    }
    public List<DonationResponseDTO> getDonationListByMemberId(String memberId){
        List<Donation> donationList = donationRepository.findAllByMemberId(memberId);
        List<DonationResponseDTO> donationResponseDTOList = new ArrayList<>();

        for (Donation donation : donationList) {
            DonationResponseDTO donationResponseDTO = new DonationResponseDTO();
            donationResponseDTO.setDonationId(donation.getId());

            Item item = itemRepository.findByDonationId(donation.getId());

            System.out.println(item);
            donationResponseDTO.setItemName(item.getName());
            donationResponseDTO.setStatus(item.getStatus());

            Market market = marketRepository.findById(item.getMarket().getId()).orElseThrow();
            donationResponseDTO.setMarketName(market.getName());

            Center center = centerRepository.findById(item.getCenter().getId()).orElseThrow();
            donationResponseDTO.setCenterName(center.getName());

            donationResponseDTOList.add(donationResponseDTO);
        }
        return donationResponseDTOList;
    }

}
