package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.Item;
import com.walkak.modakfire.dto.ItemResponseDTO;
import com.walkak.modakfire.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findItemsByCenterIdAndSortByRaisedAmount(Long id){
        List<Item> items = itemRepository.findAllByCenterId(id);
        items.sort(Item.raisedAmountComparator);
        return items;
    }
    public List<Item> findItemsByCenterIdAndSortByItemId(Long id){
        List<Item> items = itemRepository.findAllByCenterId(id);
        items.sort(Item.idComparator);
        return items;
    }
    public List<Item> findItemsByCenterId(Long id){
        return itemRepository.findAllByCenterId(id);
    }
    public ItemResponseDTO findItemByItemId(Long id){

        Item item = itemRepository.findById(id).orElseThrow();
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
        itemResponseDTO.update(item);
        return itemResponseDTO;
    }

}
