package com.walkak.modakfire.domain;

import com.walkak.modakfire.dto.SimpleItemResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    /*@EmbeddedId
    @Column(name="item_id")
    private ItemId id;*/
    private String name;
    private Long price;
    private Long raisedAmount;
    private String info;

    /*@MapsId("marketId")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @OneToOne
    @JoinColumn(name="donation_id")
    private Donation donation;

    @OneToOne
    @JoinColumn(name="periodical_donation_id")
    private PeriodicalDonation periodicalDonation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    public SimpleItemResponseDTO translate(){
        SimpleItemResponseDTO itemResponseDTO = new SimpleItemResponseDTO();
        itemResponseDTO.update(this);
        return itemResponseDTO;
    }

    public static Comparator<Item> raisedAmountComparator = new Comparator<Item>() {
        @Override
        public int compare(Item i1, Item i2) {//남은 금액 오름차순
            return Double.compare(i1.getPrice()-i1.getRaisedAmount(),i2.getPrice()-i2.getRaisedAmount());
        }
    };
    public static Comparator<Item> idComparator = new Comparator<Item>() {
        @Override
        public int compare(Item i1, Item i2) {//등록일 내림차순
            return Double.compare(i1.getId(),i2.getId());
        }
    };
}
