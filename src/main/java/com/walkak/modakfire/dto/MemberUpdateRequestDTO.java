package com.walkak.modakfire.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberUpdateRequestDTO {
    private String id;
    private String name;
    private String email;
}
