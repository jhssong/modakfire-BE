package com.walkak.modakfire.domain;

import com.walkak.modakfire.domain.EnumType.MemberRank;
import jakarta.persistence.*;
import lombok.*;

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

    /*@OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Donation> donations;*/

    /*@OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<PeriodicalDonation> periodicalDonations;*/

}
