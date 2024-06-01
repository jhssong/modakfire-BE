package com.walkak.modakfire.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="market_id")
    private Long id;
    private String name;
    private String location;
    private String Info;

    @Builder
    public Market(String name, String location, String info) {
        this.name = name;
        this.location = location;
        Info = info;
    }

    /*@OneToMany(mappedBy = "market")
    @ToString.Exclude
    private List<Item> items;*/
}
