package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.PeriodicalDonation;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PeriodicalDonationDTO {
    private Long id;
    private LocalDateTime startDate;
    private Long amount;
    private Long donationDate;
    private String centerName;
    private String centerLocation;

    @Builder
    public PeriodicalDonationDTO(Long id,LocalDateTime startDate, Long amount, Long donationDate) {
        this.id = id;
        this.startDate = startDate;
        this.amount = amount;
        this.donationDate = donationDate;
    }

    public void update(PeriodicalDonation periodicalDonation) {
        id = periodicalDonation.getId();
        startDate = periodicalDonation.getStartDate();
        amount = periodicalDonation.getAmount();
        donationDate = periodicalDonation.getDonationDate();
    }
}
