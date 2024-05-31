package com.walkak.modakfire.domain;

import com.walkak.modakfire.domain.EnumType.Status;
import com.walkak.modakfire.dto.ItemResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    private String name;
    private Long price;
    private Long raisedAmount;
    private String info;
    private Status status;
    private LocalDateTime raisingFinishedTime;
    private LocalDateTime totalFinishedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @OneToOne
    @JoinColumn(name="donation_id")
    private Donation donation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @Builder
    public Item(String name, Long price, Long raisedAmount, String info, Market market, Donation donation, Center center) {
        this.name = name;
        this.price = price;
        this.raisedAmount = raisedAmount;
        this.info = info;
        this.market = market;
        this.donation = donation;
        this.center = center;
    }

    public ItemResponseDTO translate(){
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
        itemResponseDTO.update(this);
        return itemResponseDTO;
    }

}
