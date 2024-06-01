package com.walkak.modakfire.controller;

import com.walkak.modakfire.dto.*;
import com.walkak.modakfire.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donations")
@CrossOrigin(origins = {"http://localhost:5173", "https://walkak-modakfire.web.app"})
public class DonationController {

    private final DonationService donationService;

    @PostMapping("/fast")
    public FastDonationCreateResponseDTO createFastDonation(@RequestBody FastDonationCreateRequestDTO fastDonationCreateRequestDTO) {
        return donationService.createFastDonation(fastDonationCreateRequestDTO, null);
    }

    @PostMapping
    public DonationCreateResponseDTO createDonation(@RequestBody DonationCreateRequestDTO donataDonationRequestDTO) {
        return donationService.createDonation(donataDonationRequestDTO);
    }

    @GetMapping("/{memberId}")
    public List<DonationResponseDTO> getDonationListByMemberId(@PathVariable String memberId){
        return donationService.getDonationListByMemberId(memberId);
    }
}
