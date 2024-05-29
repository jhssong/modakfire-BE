package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.EnumType.CenterType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CenterRequestDTO {
    private String city;
    private String gu;
    private CenterType centerType;
}
