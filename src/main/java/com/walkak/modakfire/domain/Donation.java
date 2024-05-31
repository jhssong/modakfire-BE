package com.walkak.modakfire.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.walkak.modakfire.dto.SimpleDonationResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="donation_id")
    private Long id;
    private LocalDateTime date;
    private Long totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    @ToString.Exclude
    private Member member;
    @Builder
    public Donation(LocalDateTime date, Long totalAmount, Long orderId, Member member) {
        this.date = date;
        this.totalAmount = totalAmount;
        this.member = member;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "donation")
    private Item item;

    public SimpleDonationResponseDTO translate(){
        SimpleDonationResponseDTO donationResponseDTO = new SimpleDonationResponseDTO();
        donationResponseDTO.update(this);
        return donationResponseDTO;
    }
}
