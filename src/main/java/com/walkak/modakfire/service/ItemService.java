package com.walkak.modakfire.service;

import com.walkak.modakfire.domain.EnumType.Status;
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

    public List<Item> findItemsByCenterId(Long id){
        return itemRepository.findAllByCenterId(id);
    }
    public ItemResponseDTO findItemByItemId(Long id){
        Item item = itemRepository.findById(id).orElseThrow();
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
        itemResponseDTO.update(item);
        return itemResponseDTO;
    }
    public void updateItemByDonation(Item item) {
        itemRepository.save(item);
    }
  
    public Status getItemStatus(Long itemId){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(()->new IllegalArgumentException("item not found with id: "+itemId));
        return item.getStatus();
    }
    @Transactional
    public ItemResponseDTO updateItemStatus(Long itemId,int status){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(()->new IllegalArgumentException("item not found with id: "+itemId));
        item.setStatus(Status.fromOrdinal(status));
        System.out.println(item);
        itemRepository.save(item);
        return item.translate();
    }

    public Item getItemEntityById(Long itemId){
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("item not found with id: " + itemId));
    }

    public ItemResponseDTO findItemByDonationId(Long donationId){
        return itemRepository.findByDonationId(donationId).translate();
    }

}
