package com.example.backend.mapper;

import com.example.backend.dto.item.ItemDto;
import com.example.backend.dto.item.ItemInputDto;
import com.example.backend.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {

    public List<ItemDto> toDto(List<Item> models) {
        return models.stream().map(this::toDto).toList();
    }

    public List<Item> fromDto(List<ItemInputDto> dtos) {
        return dtos.stream().map(this::fromDto).toList();
    }

    public ItemDto toDto(Item model) {
        return new ItemDto(model.getId(), model.getName());
    }

    public Item fromDto(ItemInputDto dto) {
        return new Item(dto.name());
    }
}
