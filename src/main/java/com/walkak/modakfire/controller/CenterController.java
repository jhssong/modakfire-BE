package com.walkak.modakfire.controller;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.Item;
import com.walkak.modakfire.dto.*;
import com.walkak.modakfire.service.CenterService;
import com.walkak.modakfire.service.ItemService;
import com.walkak.modakfire.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/centers")
public class CenterController {

    private final CenterService centerService;
    private final LikesService likesService;

    @GetMapping
    public List<CenterForLikeNumDTO> findCentersByCond(@RequestBody CenterRequestDTO centerRequestDTO){
        List<CenterResponseDTO> centersByCon = centerService.findCentersByCon(centerRequestDTO);
        List<CenterForLikeNumDTO> centerForLikeNumDTOList =new ArrayList<>();
        for (CenterResponseDTO centerResponseDTO : centersByCon) {
            CenterForLikeNumDTO centerforLikeNumDTO = new CenterForLikeNumDTO();
            centerforLikeNumDTO.update(centerResponseDTO,likesService.getLikesCountByCenterId(centerResponseDTO.getId()));
            centerForLikeNumDTOList.add(centerforLikeNumDTO);
        }
        return centerForLikeNumDTOList;
    }

    @GetMapping("/{centerId}")
    public CenterResponseDTO findCenter(@PathVariable Long centerId){
        Center center = centerService.findCenterById(centerId);
        return center.translate();
    }

}
