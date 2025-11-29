package com.example.backend.service;

import com.example.backend.model.ItemMaterial;
import com.example.backend.model.ItemMaterialId;
import com.example.backend.repository.ItemMaterialRepository;
import com.example.backend.repository.ItemRepository;
import com.example.backend.repository.ResourceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemMaterialService {

    private final ItemMaterialRepository itemMaterialRepository;
    private final ItemRepository itemRepository;
    private final ResourceRepository resourceRepository;

    public ItemMaterialService(
            ItemMaterialRepository itemMaterialRepository,
            ItemRepository itemRepository,
            ResourceRepository resourceRepository
    ) {
        this.itemMaterialRepository = itemMaterialRepository;
        this.itemRepository = itemRepository;
        this.resourceRepository = resourceRepository;
    }

    public List<ItemMaterial> findByItemId(Long itemId) {
        return itemMaterialRepository.findByItemId(itemId);
    }

    public ItemMaterial save(ItemMaterial itemMaterial) {
        if (!itemRepository.existsById(itemMaterial.getItemId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }

        if (!resourceRepository.existsById(itemMaterial.getResourceId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
        }

        return itemMaterialRepository.save(itemMaterial);
    }

    public void delete(Long itemId, Long resourceId) {
        if (!itemMaterialRepository.existsById(new ItemMaterialId(itemId, resourceId))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        itemMaterialRepository.deleteById(new ItemMaterialId(itemId, resourceId));
    }
}
