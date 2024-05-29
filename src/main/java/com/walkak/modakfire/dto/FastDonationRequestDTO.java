package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.EnumType.CenterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class FastDonationRequestDTO {
    private String userId;
    private Long totalAmount;
    private String city;
    private String gu;
    private CenterType centerType;
}
