package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.EnumType.CenterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastDonationRequestDTO {
    private Long totalAmount;
    private String city;
    private String gu;
    private CenterType centerType;
}
