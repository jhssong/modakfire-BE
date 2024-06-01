package com.walkak.modakfire.domain;

import com.walkak.modakfire.domain.EnumType.CenterType;
import com.walkak.modakfire.dto.CenterResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "center_id")
    private Long id;
    private String name;
    private String city;
    private String gu;
    @Enumerated(EnumType.ORDINAL)
    private CenterType centerType;
    private String info;
    private String location;
    private Long donorNum;
    private Long balance;

    @OneToMany(mappedBy = "center")
    @ToString.Exclude
    private List<Item> items;

    @OneToMany(mappedBy = "center")
    @ToString.Exclude
    private List<PeriodicalDonation> periodicalDonation;

    @Builder
    public Center(String name, String city, String gu, CenterType centerType, String info, String location, Long donorNum, List<Item> items) {
        this.name = name;
        this.city = city;
        this.gu = gu;
        this.centerType = centerType;
        this.info = info;
        this.location = location;
        this.donorNum = donorNum;
        this.items = items;
    }

    public CenterResponseDTO translate(){
        CenterResponseDTO centerResponseDTO = new CenterResponseDTO();
        centerResponseDTO.update(this);
        return centerResponseDTO;
    }

}
