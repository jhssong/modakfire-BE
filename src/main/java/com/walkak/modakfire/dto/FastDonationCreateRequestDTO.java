package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.EnumType.CenterType;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@NonNull
public class FastDonationCreateRequestDTO {
    private String userId;
    private Long totalAmount;
    private String city;
    private String gu;
    private CenterType centerType;
}
