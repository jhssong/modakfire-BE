package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.EnumType.CenterType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CenterForLikeNumDTO {
    private Long id;
    private String name;
    private String city;
    private String gu;
    private CenterType centerType;
    private String info;
    private String location;
    private Long donorNum;
    private Long likeNum;

    public void update(CenterResponseDTO center, long likeNum){
        id = center.getId();
        name = center.getName();
        city = center.getCity();
        gu = center.getGu();
        centerType = center.getCenterType();
        info = center.getInfo();
        location = center.getLocation();
        donorNum = center.getDonorNum();
        this.likeNum = likeNum;
    }
}
