package com.walkak.modakfire.controller;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.Item;
import com.walkak.modakfire.dto.*;
import com.walkak.modakfire.service.CenterService;
import com.walkak.modakfire.service.ItemService;
import com.walkak.modakfire.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/centers")
public class CenterController {

    private final ItemService itemService;
    private final CenterService centerService;
    private final LikesService likesService;

    /*
    Main home
     */
    @GetMapping
    public List<CenterForLikeNumDTO> viewCentersByCond(@RequestBody HomeRequestDTO homeRequestDTO){
        List<SimpleCenterResponseDTO> centersByCon = centerService.findCentersByCon(homeRequestDTO);
        List<CenterForLikeNumDTO> centerForLikeNumDTOList =new ArrayList<>();
        for (SimpleCenterResponseDTO simpleCenterResponseDTO : centersByCon) {
            CenterForLikeNumDTO centerforLikeNumDTO = new CenterForLikeNumDTO();
            centerforLikeNumDTO.update(simpleCenterResponseDTO,likesService.getLikesCountByCenterId(simpleCenterResponseDTO.getId()));
            centerForLikeNumDTOList.add(centerforLikeNumDTO);
        }
        return centerForLikeNumDTOList;
    }
    /*
    CenterHome-registerDate
     */
    @GetMapping("/registerDate/{centerId}")
    public CenterHomeResponseDTO viewCenterAndItemsByRegisterDate(@PathVariable Long centerId){
        Center center = centerService.findCenterById(centerId);
        List<Item> items = itemService.findItemsByCenterIdAndSortByItemId(centerId);
        List<SimpleItemResponseDTO> simpleItemResponseDTOList = items.stream().map(Item::translate).toList();

        CenterHomeResponseDTO centerHomeResponseDTO = new CenterHomeResponseDTO();
        CenterForLikeNumDTO centerForLikeNumDTO = new CenterForLikeNumDTO();
        centerForLikeNumDTO.update(center.translate(),likesService.getLikesCountByCenterId(centerId));
        centerHomeResponseDTO.setCenterForLikeNumDTO(centerForLikeNumDTO);
        centerHomeResponseDTO.setSimpleItemResponseDTOList(simpleItemResponseDTOList);
        return centerHomeResponseDTO;
    }
    /*
    CenterHome-raisedAmount
     */
    @GetMapping("/raisedAmount/{centerId}")
    public CenterHomeResponseDTO viewCenterAndItemsByRaisedAmount(@PathVariable Long centerId){
        Center center = centerService.findCenterById(centerId);
        List<Item> items = itemService.findItemsByCenterIdAndSortByRaisedAmount(centerId);
        List<SimpleItemResponseDTO> simpleItemResponseDTOList = items.stream().map(Item::translate).toList();

        CenterHomeResponseDTO centerHomeResponseDTO = new CenterHomeResponseDTO();
        CenterForLikeNumDTO centerForLikeNumDTO = new CenterForLikeNumDTO();
        centerForLikeNumDTO.update(center.translate(),likesService.getLikesCountByCenterId(centerId));
        centerHomeResponseDTO.setCenterForLikeNumDTO(centerForLikeNumDTO);
        centerHomeResponseDTO.setSimpleItemResponseDTOList(simpleItemResponseDTOList);
        return centerHomeResponseDTO;
    }
    /*
    CenterItemDetail
     */
    @GetMapping("/items/{itemId}")
    public SimpleItemResponseDTO viewItemDetail(@PathVariable Long itemId){
        return itemService.findItemByItemId(itemId);
    }

}
