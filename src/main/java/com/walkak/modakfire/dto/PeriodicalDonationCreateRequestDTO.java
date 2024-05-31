package com.walkak.modakfire.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PeriodicalDonationCreateRequestDTO {
    private Long amount;
    private Long donationDate;
    private String memberId;
    private Long centerId;
}
