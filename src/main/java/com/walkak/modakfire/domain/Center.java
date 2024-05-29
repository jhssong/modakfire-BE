package com.walkak.modakfire.domain;

import com.walkak.modakfire.domain.EnumType.CenterType;
import com.walkak.modakfire.dto.CenterResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "center_id")
    private Long id;
    private String name;
    private String city;
    private String gu;
    private CenterType centerType;
    private String info;
    private String location;
    private Long donorNum;

    @OneToMany(mappedBy = "center")
    @ToString.Exclude
    private List<Item> items;

    public CenterResponseDTO translate(Center center){
        CenterResponseDTO centerResponseDTO = new CenterResponseDTO();
        centerResponseDTO.update(this);
        return centerResponseDTO;
    }

}
