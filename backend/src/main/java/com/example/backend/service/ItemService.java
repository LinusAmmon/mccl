package com.example.backend.service;

import com.example.backend.model.Item;
import com.example.backend.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(UUID itemId) {
        return itemRepository.findById(itemId);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public boolean deleteById(UUID itemId) {
        if (findById(itemId).isEmpty()) {
            return false;
        }
        itemRepository.deleteById(itemId);
        return true;
    }
}
