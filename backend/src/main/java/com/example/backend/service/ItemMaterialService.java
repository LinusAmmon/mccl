package com.example.backend.service;

import com.example.backend.model.ItemMaterial;
import com.example.backend.repository.ItemMaterialRepository;
import com.example.backend.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMaterialService {

    private final ItemMaterialRepository itemMaterialRepository;
    private final ItemRepository itemRepository;

    public ItemMaterialService(ItemMaterialRepository itemMaterialRepository, ItemRepository itemRepository) {
        this.itemMaterialRepository = itemMaterialRepository;
        this.itemRepository = itemRepository;
    }

    public List<ItemMaterial> findByItemId(Long itemId) {
        return itemMaterialRepository.findByItemId(itemId);
    }

    public ItemMaterial save(ItemMaterial itemMaterial) {
        itemRepository.findById(itemMaterial.getItemId()).orElseThrow(() -> new RuntimeException("Item not found"));
        itemRepository.findById(itemMaterial.getResourceId()).orElseThrow(() -> new RuntimeException("Resource not found"));
        return itemMaterialRepository.save(itemMaterial);
    }

    public void deleteByItemIdAndResourceId(Long itemId, Long resourceId) {
        ItemMaterial existing = itemMaterialRepository.findByItemIdAndResourceId(itemId, resourceId);
        if (existing == null) {
            throw new RuntimeException("ItemMaterial not found");
        }

        itemMaterialRepository.deleteById(new ItemMaterialId(itemId, resourceId));
    }
}
