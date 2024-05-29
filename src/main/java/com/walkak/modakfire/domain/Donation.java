package com.walkak.modakfire.domain;

import com.walkak.modakfire.dto.DonationResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="donation_id")
    private Long id;
    private LocalDateTime date;
    private Long totalAmount;
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "donation")
    private Item item;

    public DonationResponseDTO translate() {
        DonationResponseDTO donationResponseDTO = new DonationResponseDTO();
        donationResponseDTO.update(this);
        return donationResponseDTO;
    }

}
