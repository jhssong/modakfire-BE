package com.walkak.modakfire.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PeriodicalDonationUpdateResponseDTO {
    private Long id;
    private LocalDateTime startDate;
    private Long amount;
    private Long donationDate;
    private Long centerId;

    @Builder
    public PeriodicalDonationUpdateResponseDTO(Long id, LocalDateTime startDate, Long amount, Long donationDate,Long centerId) {
        this.id = id;
        this.startDate = startDate;
        this.amount = amount;
        this.donationDate = donationDate;
        this.centerId = centerId;
    }
}
