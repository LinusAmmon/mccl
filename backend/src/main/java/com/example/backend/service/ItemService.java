package com.example.backend.service;

import com.example.backend.model.Item;
import com.example.backend.repository.ItemMaterialRepository;
import com.example.backend.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMaterialRepository itemMaterialRepository;

    public ItemService(ItemRepository itemRepository,
                       ItemMaterialRepository itemMaterialRepository) {
        this.itemRepository = itemRepository;
        this.itemMaterialRepository = itemMaterialRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Item update(Long itemId, Item item) {
        if (itemRepository.existsById(itemId)) {
            item.setId(itemId);
            return itemRepository.save(item);
        } else {
            throw new RuntimeException("Item not found");
        }
    }

    @Transactional
    public boolean deleteById(Long itemId) {
        if (findById(itemId).isEmpty()) {
            return false;
        }
        itemMaterialRepository.deleteByItemId(itemId);
        itemRepository.deleteById(itemId);
        return true;
    }
}
