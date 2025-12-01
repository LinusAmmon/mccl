package com.example.backend.mapper;

import com.example.backend.dto.item.ItemDto;
import com.example.backend.dto.item.ItemInputDto;
import com.example.backend.model.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemMapperTest {

    private final ItemMapper mapper = new ItemMapper();

    @Test
    void givenItem_whenToDto_thenReturnItemDto() {
        Item item = new Item(1L, "Item");

        ItemDto result = mapper.toDto(item);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("Item");
    }

    @Test
    void givenItemDto_whenFromDto_thenReturnItem() {
        ItemInputDto dto = new ItemInputDto("Item");

        Item result = mapper.fromDto(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNull();
        assertThat(result.getName()).isEqualTo("Item");
    }

    @Test
    void givenListOfItems_whenToDto_thenReturnListOfItemDtos() {
        Item item1 = new Item(1L, "Item 1");
        Item item2 = new Item(2L, "Item 2");

        List<Item> items = List.of(item1, item2);

        List<ItemDto> result = mapper.toDto(items);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).id()).isEqualTo(1L);
        assertThat(result.get(1).id()).isEqualTo(2L);
        assertThat(result.get(0).name()).isEqualTo("Item 1");
        assertThat(result.get(1).name()).isEqualTo("Item 2");
    }
}
