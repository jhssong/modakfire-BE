package com.walkak.modakfire.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodicalDonation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodical_donation_id")
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    /*@OneToOne(mappedBy = "periodicalDonation")
    private Item item;*/

}
