package com.walkak.modakfire.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;*/
    @EmbeddedId
    private ItemId id;
    private String name;
    private Long price;
    private Long raisedAmount;
    private String info;

    @MapsId("marketId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @OneToOne(mappedBy = "item")
    @JoinColumn(name="donation_id")
    private Donation donation;

    @OneToOne(mappedBy = "item")
    @JoinColumn(name="periodical_donation_id")
    private PeriodicalDonation periodicalDonation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

}
