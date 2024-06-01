package com.walkak.modakfire.domain;

import com.walkak.modakfire.dto.PeriodicalDonationDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PeriodicalDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodical_donation_id")
    private Long id;
    private LocalDateTime startDate;
    private Long amount;
    private Long donationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @Builder
    public PeriodicalDonation(LocalDateTime startDate, Long amount, Member member,Long donationDate,Center center) {
        this.startDate = startDate;
        this.amount = amount;
        this.donationDate = donationDate;
        this.member = member;
        this.center = center;
    }

    public PeriodicalDonationDTO translate(){
        PeriodicalDonationDTO periodicalDonationDTO = new PeriodicalDonationDTO();
        periodicalDonationDTO.update(this);
        return periodicalDonationDTO;
    }
}
