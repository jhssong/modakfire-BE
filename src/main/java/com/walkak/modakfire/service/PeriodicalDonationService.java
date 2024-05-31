package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.Member;
import com.walkak.modakfire.domain.PeriodicalDonation;
import com.walkak.modakfire.dto.*;
import com.walkak.modakfire.repository.CenterRepository;
import com.walkak.modakfire.repository.MemberRepository;
import com.walkak.modakfire.repository.PeriodicalDonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PeriodicalDonationService {

    private final PeriodicalDonationRepository periodicalDonationRepository;
    private final CenterRepository centerRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public PeriodicalDonationCreateResponseDTO createPeriodicalDonation(PeriodicalDonationCreateRequestDTO requestDTO){
        Member findMember = memberRepository.findById(requestDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + requestDTO.getMemberId()));
        Center findCenter = centerRepository.findById(requestDTO.getCenterId())
                .orElseThrow(() -> new IllegalArgumentException("Center not found with id: " + requestDTO.getCenterId()));

        PeriodicalDonation periodicalDonation = PeriodicalDonation.builder()
                .amount(requestDTO.getAmount())
                .startDate(LocalDateTime.now())
                .donationDate(requestDTO.getDonationDate())
                .member(findMember)
                .center(findCenter).build();
        periodicalDonationRepository.save(periodicalDonation);

        return PeriodicalDonationCreateResponseDTO.builder()
                .id(periodicalDonation.getId())
                .amount(periodicalDonation.getAmount())
                .startDate(periodicalDonation.getStartDate())
                .donationDate(periodicalDonation.getDonationDate())
                .memberId(periodicalDonation.getMember().getId())
                .centerId(periodicalDonation.getCenter().getId()).build();
    }

    public List<PeriodicalDonationDTO> findPeriodicalDonationListByMemberId(String memberId){
        List<PeriodicalDonationDTO> periodicalDonationDTOList = new ArrayList<>();

        List<PeriodicalDonation> periodicalDonations = periodicalDonationRepository.findAllByMemberId(memberId);
        for (PeriodicalDonation periodicalDonation : periodicalDonations) {
            PeriodicalDonationDTO periodicalDonationDTO = PeriodicalDonationDTO.builder()
                    .id(periodicalDonation.getId())
                    .amount(periodicalDonation.getAmount())
                    .startDate(periodicalDonation.getStartDate())
                    .donationDate(periodicalDonation.getDonationDate())
                    .build();
            Center center = centerRepository.findByPeriodicalDonationId(periodicalDonation.getId());
            periodicalDonationDTO.setCenterName(center.getName());
            periodicalDonationDTO.setCenterLocation(center.getLocation());
            periodicalDonationDTOList.add(periodicalDonationDTO);
        }
        return periodicalDonationDTOList;
    }

    @Transactional
    public PeriodicalDonationUpdateResponseDTO editPeriodicalDonationInfo(PeriodicalDonationUpdateRequestDTO param, Long periodicalDonationId){
        PeriodicalDonation periodicalDonation = periodicalDonationRepository.findById(periodicalDonationId)
                .orElseThrow(() -> new IllegalArgumentException("PeriodicalDonation not found with id: " + periodicalDonationId));;
        periodicalDonation.setStartDate(LocalDateTime.now());
        periodicalDonation.setAmount(param.getAmount());
        periodicalDonation.setDonationDate(param.getDonationDate());
        Center center = centerRepository.findById(param.getCenterId()).orElseThrow();
        periodicalDonation.setCenter(center);

        periodicalDonationRepository.save(periodicalDonation);

        return PeriodicalDonationUpdateResponseDTO.builder()
                .id(periodicalDonation.getId())
                .amount(periodicalDonation.getAmount())
                .startDate(periodicalDonation.getStartDate())
                .donationDate(periodicalDonation.getDonationDate())
                .centerId(center.getId())
                .build();
    }

    @Transactional
    public String deletePeriodicalDonation(Long id){
        periodicalDonationRepository.deleteById(id);
        return "Deleted";
    }
}
