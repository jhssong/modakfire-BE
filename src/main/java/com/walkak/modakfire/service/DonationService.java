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

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final CenterService centerService;

    public DonationResponseDTO createFastDonation(FastDonationRequestDTO fastDonationRequestDTO) {
        // Find centers to donate
        CenterRequestDTO centerRequestDTO = new CenterRequestDTO(fastDonationRequestDTO.);
        List<Center> centerList = centerService.findCentersByCon()

        // Find items at


        return new DonationResponseDTO();
    }
}
