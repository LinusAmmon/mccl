package com.example.backend.service;

import com.example.backend.dto.ItemMaterialDto;
import com.example.backend.model.ItemMaterial;
import com.example.backend.repository.ItemMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ItemMaterialService {

    private final ItemMaterialRepository itemMaterialRepository;

    public ItemMaterialService(ItemMaterialRepository itemMaterialRepository) {
        this.itemMaterialRepository = itemMaterialRepository;
    }

    public List<ItemMaterialDto> findByItemId(UUID id) {
        List<ItemMaterialDto> response = new ArrayList<>();
        List<ItemMaterial> itemMaterials = itemMaterialRepository.findByItemId(id);
        itemMaterials.forEach(itemMaterial ->
                response.add(new ItemMaterialDto(itemMaterial.getResourceId(), itemMaterial.getAmount())));
        return response;
    }

    public ItemMaterial save(UUID itemId, UUID resourceId, long amount) {
        return itemMaterialRepository.save(new ItemMaterial(itemId, resourceId, amount));
    }

    public boolean deleteByItemId(UUID itemId) {
        if (findByItemId(itemId).isEmpty()) {
            return false;
        }
        itemMaterialRepository.deleteByItemId(itemId);
        return true;
    }
}
