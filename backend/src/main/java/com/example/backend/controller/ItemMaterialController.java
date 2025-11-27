package com.example.backend.controller;

import com.example.backend.dto.ItemMaterialDto;
import com.example.backend.model.ItemMaterial;
import com.example.backend.service.ItemMaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items/{itemId}/materials")
public class ItemMaterialController {

    private final ItemMaterialService service;

    public ItemMaterialController(ItemMaterialService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ItemMaterialDto>> findByItemId(@PathVariable UUID itemId) {
        return ResponseEntity.ok(service.findByItemId(itemId));
    }

    @PutMapping
    public ResponseEntity<ItemMaterial> save(@PathVariable UUID itemId, @RequestBody ItemMaterialDto dto) {
        return ResponseEntity.ok(service.save(itemId, dto.resourceId(), dto.amount()));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteByItemId(@PathVariable UUID itemId) {
        return service.deleteByItemId(itemId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
