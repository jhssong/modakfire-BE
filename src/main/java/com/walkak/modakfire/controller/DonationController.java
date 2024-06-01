package com.walkak.modakfire.controller;

import com.walkak.modakfire.dto.*;
import com.walkak.modakfire.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donations")
@CrossOrigin(origins = {"http://localhost:5173", "https://port-0-modakfire-be-1272llwutmz86.sel5.cloudtype.app/api"})
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
    public List<DonationDetailDTO> getDonationListByMemberId(@PathVariable String memberId){
        return donationService.getDonationListByMemberId(memberId);
    }

    @GetMapping("/timeInfo/{donationId}")
    public DonationTimeInfoDTO getDonationTimeInfoById(@PathVariable Long donationId){
        return donationService.getDonationTimeInfoById(donationId);
    }
}
