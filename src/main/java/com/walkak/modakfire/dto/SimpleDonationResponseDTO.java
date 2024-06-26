package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.Donation;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SimpleDonationResponseDTO {
    private Long id;
    private LocalDateTime date;
    private Long totalAmount;
    private Long orderId;
    private Long itemId;
    private String centerName;
    private String itemName;

    public void update(Donation donation){
        id = donation.getId();
        date = donation.getDate();
        totalAmount = donation.getTotalAmount();
        orderId = donation.getOrderId();
        itemId = donation.getItem().getId();
        itemName = donation.getItem().getName();
        centerName = donation.getItem().getCenter().getName();
    }
}