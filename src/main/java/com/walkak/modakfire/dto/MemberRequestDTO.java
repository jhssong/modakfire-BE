package com.walkak.modakfire.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRequestDTO {
    private String id;
    private String name;
    private String email;
    private String registerDate;
}
