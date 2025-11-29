package com.example.backend.service;

import com.example.backend.model.Item;
import com.example.backend.repository.ItemMaterialRepository;
import com.example.backend.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMaterialRepository itemMaterialRepository;

    public ItemService(ItemRepository itemRepository, ItemMaterialRepository itemMaterialRepository) {
        this.itemRepository = itemRepository;
        this.itemMaterialRepository = itemMaterialRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Item update(Long itemId, Item item) {
        if (itemRepository.existsById(itemId)) {
            item.setId(itemId);
            return itemRepository.save(item);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (itemRepository.findById(id).isPresent()) {
            itemRepository.deleteById(id);
            itemMaterialRepository.deleteByItemId(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
    }
}
