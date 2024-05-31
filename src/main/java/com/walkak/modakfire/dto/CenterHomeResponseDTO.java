package com.walkak.modakfire.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CenterHomeResponseDTO {
    CenterForLikeNumDTO centerForLikeNumDTO;
    List<ItemResponseDTO> itemResponseDTOList;
}
