package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.PeriodicalDonation;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PeriodicalDonationCreateResponseDTO {
    private Long id;
    private LocalDateTime startDate;
    private Long amount;
    private Long donationDate;
    private String memberId;
    private Long centerId;

    @Builder
    public PeriodicalDonationCreateResponseDTO(Long id, LocalDateTime startDate, Long amount, Long donationDate, String memberId, Long centerId) {
        this.id = id;
        this.startDate = startDate;
        this.amount = amount;
        this.donationDate = donationDate;
        this.memberId = memberId;
        this.centerId = centerId;
    }
}
