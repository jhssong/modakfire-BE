package com.walkak.modakfire.controller;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.Item;
import com.walkak.modakfire.dto.CenterItemsResponseDTO;
import com.walkak.modakfire.dto.CenterResponseDTO;
import com.walkak.modakfire.dto.CenterRequestDTO;
import com.walkak.modakfire.dto.ItemResponseDTO;
import com.walkak.modakfire.service.CenterService;
import com.walkak.modakfire.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/centers")
public class CenterController {

    private final ItemService itemService;
    private final CenterService centerService;

    /*
    Main home
     */
    @GetMapping
    public List<CenterResponseDTO> viewCentersByCond(@RequestBody CenterRequestDTO centerRequestDTO){
        return centerService.findCentersByCon(centerRequestDTO);
    }
    /*
    CenterHome-registerDate
     */
    @GetMapping("/registerDate/{centerId}")
    public CenterItemsResponseDTO viewCenterAndItemsByRegisterDate(@PathVariable Long centerId){
        Center center = centerService.findCenterById(centerId);
        List<Item> items = itemService.findItemsByCenterIdAndSortByItemId(centerId);
        List<ItemResponseDTO> itemResponseDTOList = items.stream().map(Item::translate).toList();

        CenterItemsResponseDTO centerItemsResponseDTO = new CenterItemsResponseDTO();
        centerItemsResponseDTO.setCenter(center);
        centerItemsResponseDTO.setItemResponseDTOList(itemResponseDTOList);
        return centerItemsResponseDTO;
    }
    /*
    CenterHome-raisedAmount
     */
    @GetMapping("/raisedAmount/{centerId}")
    public CenterItemsResponseDTO viewCenterAndItemsByRaisedAmount(@PathVariable Long centerId){
        Center center = centerService.findCenterById(centerId);
        List<Item> items = itemService.findItemsByCenterIdAndSortByRaisedAmount(centerId);
        List<ItemResponseDTO> itemResponseDTOList = items.stream().map(Item::translate).toList();

        CenterItemsResponseDTO centerItemsResponseDTO = new CenterItemsResponseDTO();
        centerItemsResponseDTO.setCenter(center);
        centerItemsResponseDTO.setItemResponseDTOList(itemResponseDTOList);
        return centerItemsResponseDTO;
    }
    /*
    CenterItemDetail
     */
    @GetMapping("/{itemId}")
    public ItemResponseDTO viewItemDetail(@PathVariable Long itemId){
        return itemService.findItemByItemId(itemId);
    }

}
