package com.example.backend.mapper;

import com.example.backend.dto.itemMaterial.ItemMaterialDto;
import com.example.backend.model.ItemMaterial;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMaterialMapper {

    public List<ItemMaterialDto> toDto(List<ItemMaterial> models) {
        return models.stream().map(this::toDto).toList();
    }

    public ItemMaterialDto toDto(ItemMaterial model) {
        return new ItemMaterialDto(model.getResourceId(), model.getAmount());
    }

    public ItemMaterial fromDto(Long itemId, ItemMaterialDto dto) {
        return new ItemMaterial(itemId, dto.resourceId(), dto.amount());
    }
}
