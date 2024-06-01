package com.walkak.modakfire.controller;


import com.walkak.modakfire.domain.EnumType.Status;
import com.walkak.modakfire.domain.Item;
import com.walkak.modakfire.dto.ItemResponseDTO;
import com.walkak.modakfire.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
@CrossOrigin(origins = {"http://localhost:5173", "https://port-0-modakfire-be-1272llwutmz86.sel5.cloudtype.app/api"})
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/center/{centerId}")
    public List<ItemResponseDTO> findItemsByCenterId(@PathVariable Long centerId){
        List<Item> items = itemService.findItemsByCenterId(centerId);
        return items.stream().map(Item::translate).toList();
    }

    @GetMapping("/{itemId}")
    public ItemResponseDTO getItemDetail(@PathVariable Long itemId){
        return itemService.findItemByItemId(itemId);
    }

    @GetMapping("/status/{itemId}")
    public Status getItemStatus(@PathVariable Long itemId){
        return itemService.getItemStatus(itemId);
    }

    @PutMapping("/status/{itemId}")
    public ItemResponseDTO updateItemStatus(@PathVariable Long itemId,@RequestParam int status){
        return itemService.updateItemStatus(itemId,status);
    }
}
