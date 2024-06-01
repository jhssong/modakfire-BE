package com.walkak.modakfire.controller;

import com.walkak.modakfire.dto.*;
import com.walkak.modakfire.service.PeriodicalDonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/periodicalDonations")
@CrossOrigin(origins = {"http://localhost:5173", "https://port-0-modakfire-be-1272llwutmz86.sel5.cloudtype.app/api"})
public class PeriodicalDonationController {

    private final PeriodicalDonationService periodicalDonationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PeriodicalDonationCreateResponseDTO createPeriodicalDonation(@RequestBody PeriodicalDonationCreateRequestDTO dto){
        return periodicalDonationService.createPeriodicalDonation(dto);
    }

    @GetMapping("/{memberId}")
    public List<PeriodicalDonationDTO> viewPeriodicalDonationListByMemberId(@PathVariable String memberId){
        return periodicalDonationService.findPeriodicalDonationListByMemberId(memberId);
    }

    @PutMapping("/{periodicalDonationId}")
    public PeriodicalDonationUpdateResponseDTO editPeriodicalDonation(@RequestBody PeriodicalDonationUpdateRequestDTO param, @PathVariable Long periodicalDonationId){
        return periodicalDonationService.editPeriodicalDonationInfo(param,periodicalDonationId);
    }

    @DeleteMapping("/{periodicalDonationId}")
    public String deletePeriodicalDonation(@PathVariable Long periodicalDonationId){
        return periodicalDonationService.deletePeriodicalDonation(periodicalDonationId);
    }
}
