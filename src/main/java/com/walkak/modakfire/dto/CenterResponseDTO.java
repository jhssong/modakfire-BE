package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.EnumType.CenterType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CenterResponseDTO {
    private Long id;
    private String name;
    private String city;
    private String gu;
    private CenterType centerType;
    private String info;
    private Long donorNum;

    public void update(Center center){
        id = center.getId();
        name = center.getName();
        city = center.getCity();
        gu = center.getGu();
        centerType = center.getCenterType();
        info = center.getInfo();
        donorNum = center.getDonorNum();
    }
}
