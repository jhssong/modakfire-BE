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
@CrossOrigin(origins = {"http://localhost:5173", "https://walkak-modakfire.web.app"})
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/center/{centerId}")
    public List<ItemResponseDTO> findItemsByCenterId(@PathVariable Long centerId){
        return itemService.findItemListByCenterId(centerId);
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
    @GetMapping("/detail/{donationId}")
    public ItemResponseDTO getItemInfoByDonationId(@PathVariable Long donationId){
        return itemService.findItemByDonationId(donationId);
    }
}
