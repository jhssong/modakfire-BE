package com.walkak.modakfire.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.walkak.modakfire.domain.EnumType.Status;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class DonationDetailIResponseDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime raisingFinishedTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime totalFinishedTime;
    Long itemId;
    Status status;
    Long totalAmount;
}
