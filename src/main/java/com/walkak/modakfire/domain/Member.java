package com.walkak.modakfire.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String email;
    private MemberRank memberRank;
    private LocalDateTime registerDate;

    @OneToMany(mappedBy = "member")
    private List<Donation> donations;

    @OneToMany(mappedBy = "member")
    private List<PeriodicalDonation> periodicalDonations;

}
