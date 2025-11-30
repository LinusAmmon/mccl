package com.example.backend.service;

import com.example.backend.model.ItemMaterial;
import com.example.backend.model.ItemMaterialId;
import com.example.backend.repository.ItemMaterialRepository;
import com.example.backend.repository.ItemRepository;
import com.example.backend.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemMaterialServiceTest {

    private ItemMaterial itemMaterial;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private ItemMaterialRepository itemMaterialRepository;

    @InjectMocks
    private ItemMaterialService itemMaterialService;

    @BeforeEach
    void setUp() {
        itemMaterial = new ItemMaterial(1L, 2L, 10L);
    }

    @Test
    void givenItemIdWithMaterials_whenFindByItemId_thenReturnMaterials() {
        when(itemMaterialRepository.findByItemId(1L)).thenReturn(List.of(itemMaterial));

        List<ItemMaterial> result = itemMaterialService.findByItemId(1L);

        assertThat(result).containsExactly(itemMaterial);
        verify(itemMaterialRepository).findByItemId(1L);
    }

    @Test
    void givenItemIdWithNoMaterials_whenFindByItemId_thenReturnEmptyList() {
        when(itemMaterialRepository.findByItemId(1L)).thenReturn(List.of());

        List<ItemMaterial> result = itemMaterialService.findByItemId(1L);

        assertThat(result).isEmpty();
        verify(itemMaterialRepository).findByItemId(1L);
    }

    @Test
    void givenValidItemMaterial_whenSave_thenItemMaterialIsSaved() {
        when(itemRepository.existsById(1L)).thenReturn(true);
        when(resourceRepository.existsById(2L)).thenReturn(true);
        when(itemMaterialRepository.save(itemMaterial)).thenReturn(itemMaterial);

        ItemMaterial result = itemMaterialService.save(itemMaterial);

        assertThat(result).isEqualTo(itemMaterial);
        verify(itemRepository).existsById(1L);
        verify(resourceRepository).existsById(2L);
        verify(itemMaterialRepository).save(itemMaterial);
    }

    @Test
    void givenNonExistingItem_whenSave_thenThrowsException() {
        when(itemRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> itemMaterialService.save(itemMaterial))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Item not found");

        verify(itemRepository).existsById(1L);
        verify(resourceRepository, never()).existsById(anyLong());
        verify(itemMaterialRepository, never()).save(any());
    }

    @Test
    void givenNonExistingResource_whenSave_thenThrowsException() {
        when(itemRepository.existsById(1L)).thenReturn(true);
        when(resourceRepository.existsById(2L)).thenReturn(false);

        assertThatThrownBy(() -> itemMaterialService.save(itemMaterial))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Resource not found");

        verify(itemRepository).existsById(1L);
        verify(resourceRepository).existsById(2L);
        verify(itemMaterialRepository, never()).save(any());
    }

    @Test
    void givenExistingItemMaterial_whenDelete_thenItemMaterialIsDeleted() {
        ItemMaterialId id = new ItemMaterialId(1L, 2L);
        when(itemMaterialRepository.existsById(id)).thenReturn(true);

        itemMaterialService.delete(1L, 2L);

        verify(itemMaterialRepository).existsById(id);
        verify(itemMaterialRepository).deleteById(id);
    }

    @Test
    void givenNonExistingItemMaterial_whenDelete_thenThrowsException() {
        ItemMaterialId id = new ItemMaterialId(1L, 2L);
        when(itemMaterialRepository.existsById(id)).thenReturn(false);

        assertThatThrownBy(() -> itemMaterialService.delete(1L, 2L))
                .isInstanceOf(ResponseStatusException.class);

        verify(itemMaterialRepository).existsById(id);
        verify(itemMaterialRepository, never()).deleteById(any());
    }
}
