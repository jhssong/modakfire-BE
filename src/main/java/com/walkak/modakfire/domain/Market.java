package com.walkak.modakfire.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Market {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String Info;

    /*@OneToMany(mappedBy = "market")
    @ToString.Exclude
    private List<Item> items;*/
}
