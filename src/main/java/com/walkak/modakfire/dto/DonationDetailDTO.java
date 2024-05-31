package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.EnumType.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DonationDetailDTO {
    Long donationId;
    String itemName;
    String centerName;
    String marketName;
    Status status;
}
