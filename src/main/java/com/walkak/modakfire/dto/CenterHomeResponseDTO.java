package com.walkak.modakfire.dto;

import com.walkak.modakfire.domain.Center;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CenterItemsResponseDTO {
    CenterResponseDTO centerResponseDTO;
    List<ItemResponseDTO> itemResponseDTOList;
}
