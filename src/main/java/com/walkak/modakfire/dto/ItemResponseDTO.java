package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemResponseDTO {
    private Long id;
    private String name;
    private Long price;
    private Long raisedAmount;
    private String info;
    private String marketName;
    private Long centerId;

    public void update(Item item){
        id = item.getId();
        name = item.getName();
        price = item.getPrice();
        raisedAmount = item.getRaisedAmount();
        info = item.getInfo();
        marketName = item.getMarket().getName();
        centerId = item.getCenter().getId();
    }
}
