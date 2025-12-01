package com.example.backend.mapper;

import com.example.backend.dto.itemMaterial.ItemMaterialDto;
import com.example.backend.model.ItemMaterial;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemMaterialMapperTest {

    private final ItemMaterialMapper mapper = new ItemMaterialMapper();

    @Test
    void givenItemMaterial_whenToDto_thenReturnItemMaterialDto() {
        ItemMaterial itemMaterial = new ItemMaterial(1L, 2L, 3L);

        ItemMaterialDto result = mapper.toDto(itemMaterial);

        assertThat(result).isNotNull();
        assertThat(result.resourceId()).isEqualTo(2L);
        assertThat(result.amount()).isEqualTo(3L);
    }

    @Test
    void givenItemIdAndItemMaterialDto_whenFromDto_thenReturnItemMaterial() {
        ItemMaterialDto dto = new ItemMaterialDto(2L, 3L);

        ItemMaterial result = mapper.fromDto(1L, dto);

        assertThat(result).isNotNull();
        assertThat(result.getItemId()).isEqualTo(1L);
        assertThat(result.getResourceId()).isEqualTo(2L);
        assertThat(result.getAmount()).isEqualTo(3L);
    }

    @Test
    void givenListOfItemMaterials_whenToDto_thenReturnListOfItemMaterialDtos() {
        ItemMaterial itemMaterial1 = new ItemMaterial(1L, 2L, 3L);
        ItemMaterial itemMaterial2 = new ItemMaterial(4L, 5L, 6L);

        List<ItemMaterial> itemMaterials = List.of(itemMaterial1, itemMaterial2);

        List<ItemMaterialDto> result = mapper.toDto(itemMaterials);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).resourceId()).isEqualTo(2L);
        assertThat(result.get(0).amount()).isEqualTo(3L);
        assertThat(result.get(1).resourceId()).isEqualTo(5L);
        assertThat(result.get(1).amount()).isEqualTo(6L);
    }
}
