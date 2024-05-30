package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.Donation;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DonationResponseDTO {
    private Long id;
    private LocalDateTime date;
    private Long totalAmount;
    private Long orderId;
    private Long itemId;

    public void update(Donation donation){
        id = donation.getId();
        date = donation.getDate();
        totalAmount = donation.getTotalAmount();
        orderId = donation.getOrderId();
        itemId = donation.getItem().getId();
    }
}
