package com.walkak.modakfire.controller;

import com.walkak.modakfire.dto.DonationRequestDTO;
import com.walkak.modakfire.dto.FastDonationRequestDTO;
import com.walkak.modakfire.dto.DonationResponseDTO;
import com.walkak.modakfire.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donations")
@CrossOrigin(origins = "http://localhost:5173")
public class DonationController {

    private final DonationService donationService;

    @PostMapping("/fast")
    public DonationResponseDTO createFastDonation(@RequestBody FastDonationRequestDTO fastDonationRequestDTO) {
        return donationService.createFastDonation(fastDonationRequestDTO, null);
    }

    @PostMapping()
    public DonationResponseDTO createDonation(@RequestBody DonationRequestDTO donataDonationRequestDTO) {
        return donationService.createDonation(donataDonationRequestDTO);
    }
}