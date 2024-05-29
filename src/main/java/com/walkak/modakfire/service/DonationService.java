package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.EnumType.CenterType;
import com.walkak.modakfire.domain.Item;
import com.walkak.modakfire.dto.*;
import com.walkak.modakfire.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final CenterService centerService;
    private final ItemService itemService;

    public DonationResponseDTO createFastDonation(FastDonationRequestDTO fastDonationRequestDTO) {
        // Find centers to donate
        CenterRequestDTO centerRequestDTO = new CenterRequestDTO(
                fastDonationRequestDTO.getCity(),
                fastDonationRequestDTO.getGu(),
                fastDonationRequestDTO.getCenterType());
        List<CenterResponseDTO> centerList = centerService.findCentersByCon(centerRequestDTO);

        // Find items at random center
        Random rand = new Random();
        CenterResponseDTO randomCenter = centerList.get(rand.nextInt(centerList.size()));

        // Get ItemList from center
        List<Item> itemList = itemService.findItemsByCenterIdAndSortByItemId(randomCenter.getId());
        


        return new DonationResponseDTO();
    }
}
