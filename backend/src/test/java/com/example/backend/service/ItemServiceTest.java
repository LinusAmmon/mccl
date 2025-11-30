package com.example.backend.service;

import com.example.backend.model.Item;
import com.example.backend.repository.ItemMaterialRepository;
import com.example.backend.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    private Item item;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMaterialRepository itemMaterialRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        item = new Item();
    }

    @Test
    void givenMultipleItemsInRepository_whenFindAll_thenAllItemsAreReturned() {
        when(itemRepository.findAll()).thenReturn(List.of(item, item));

        List<Item> result = itemService.findAll();

        assertThat(result).containsExactly(item, item);

        verify(itemRepository).findAll();
    }

    @Test
    void givenEmptyRepository_whenFindAll_thenEmptyListIsReturned() {
        when(itemRepository.findAll()).thenReturn(List.of());

        List<Item> result = itemService.findAll();

        assertThat(result).isEmpty();

        verify(itemRepository).findAll();
    }

    @Test
    void givenExistingItemId_whenFindById_thenItemIsReturned() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Item result = itemService.findById(1L);

        assertThat(result).isEqualTo(item);

        verify(itemRepository).findById(1L);
    }

    @Test
    void givenNonExistingItemId_whenFindById_thenThrowsException() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> itemService.findById(1L))
                .isInstanceOf(ResponseStatusException.class);

        verify(itemRepository).findById(1L);
    }

    @Test
    void givenValidItem_whenCreate_thenReturnSavedItem() {
        when(itemRepository.save(item)).thenReturn(item);

        Item result = itemService.create(item);

        assertThat(result).isEqualTo(item);

        verify(itemRepository).save(item);
    }

    @Test
    void givenExistingItemIdAndValidItem_whenUpdate_thenItemIsUpdated() {
        when(itemRepository.existsById(1L)).thenReturn(true);
        when(itemRepository.save(item)).thenReturn(item);

        Item result = itemService.update(1L, item);

        assertThat(result).isEqualTo(item);
        assertThat(result.getId()).isEqualTo(1L);

        verify(itemRepository).existsById(1L);
        verify(itemRepository).save(item);
    }

    @Test
    void givenNonExistingItemId_whenUpdate_thenThrowsException() {
        when(itemRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> itemService.update(1L, item))
                .isInstanceOf(ResponseStatusException.class);

        verify(itemRepository).existsById(1L);
        verify(itemRepository, never()).save(item);
    }

    @Test
    void givenExistingItemId_whenDelete_thenItemAndRelationsAreDeleted() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        itemService.delete(1L);

        verify(itemRepository).findById(1L);
        verify(itemRepository).deleteById(1L);
        verify(itemMaterialRepository).deleteByItemId(1L);
    }

    @Test
    void givenNonExistingItemId_whenDelete_thenThrowsException() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> itemService.delete(1L))
                .isInstanceOf(ResponseStatusException.class);

        verify(itemRepository).findById(1L);
        verify(itemRepository, never()).deleteById(1L);
        verify(itemMaterialRepository, never()).deleteByItemId(1L);
    }
}
