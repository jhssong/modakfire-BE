package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.EnumType.CenterType;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.walkak.modakfire.domain.EnumType.CenterType.WHOLE;

@Data
@NoArgsConstructor
public class CenterRequestDTO {
    private String city;
    private String gu;
    private CenterType centerType;

    public boolean isCityWhole(){
        return city.equals("전체");
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
