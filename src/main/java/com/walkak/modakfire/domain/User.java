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
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;
    private Rank rank;
    private LocalDateTime registerDate;

    @OneToMany(mappedBy = "user")
    private List<Donation> donations;

    @OneToMany(mappedBy = "user")
    private List<PeriodicalDonation> periodicalDonations;

}
