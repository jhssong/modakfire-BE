package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.EnumType.CenterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.walkak.modakfire.domain.EnumType.CenterType.WHOLE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenterRequestDTO {
    private String city;
    private String gu;
    private CenterType centerType;
    @Builder
    public CenterRequestDTO(String city, String gu, CenterType centerType) {
        this.city = city;
        this.gu = gu;
        this.centerType = centerType;
    }
    public boolean isCityWhole(){
        return city.equals("전국");
    }
    public boolean isGuWhole(){
        return gu.equals("전체");
    }
    public boolean isCenterTypeWhole(){
        return centerType.equals(WHOLE);
    }
    public boolean isAllWhole(){
        return isCityWhole()&&isGuWhole()&&isCenterTypeWhole();
    }
}
