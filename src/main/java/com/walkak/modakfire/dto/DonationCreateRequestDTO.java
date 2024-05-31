package com.walkak.modakfire.dto;

import lombok.Data;

@Data
public class DonationCreateRequestDTO {
    private String memberId;
    private Long itemId;
    private Long totalAmount;
}
