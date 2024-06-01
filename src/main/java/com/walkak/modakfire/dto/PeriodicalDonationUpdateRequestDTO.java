package com.walkak.modakfire.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PeriodicalDonationUpdateRequestDTO {
    private Long amount;
    private Long donationDate;
    private Long centerId;
}
