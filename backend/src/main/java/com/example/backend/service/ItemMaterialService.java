package com.example.backend.service;

import com.example.backend.dto.itemMaterial.ItemMaterialDto;
import com.example.backend.model.ItemMaterial;
import com.example.backend.repository.ItemMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemMaterialService {

    private final ItemMaterialRepository itemMaterialRepository;

    public ItemMaterialService(ItemMaterialRepository itemMaterialRepository) {
        this.itemMaterialRepository = itemMaterialRepository;
    }

    public List<ItemMaterialDto> findByItemId(Long itemId) {
        List<ItemMaterialDto> response = new ArrayList<>();
        List<ItemMaterial> itemMaterials = itemMaterialRepository.findByItemId(itemId);
        itemMaterials.forEach(itemMaterial ->
                response.add(new ItemMaterialDto(itemMaterial.getResourceId(), itemMaterial.getAmount())));
        return response;
    }

    public ItemMaterial save(Long itemId, Long resourceId, Long amount) {
        return itemMaterialRepository.save(new ItemMaterial(itemId, resourceId, amount));
    }

    public boolean deleteByItemId(Long itemId) {
        if (findByItemId(itemId).isEmpty()) {
            return false;
        }
        itemMaterialRepository.deleteByItemId(itemId);
        return true;
    }
}
