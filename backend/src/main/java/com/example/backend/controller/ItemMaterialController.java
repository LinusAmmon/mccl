package com.example.backend.controller;

import com.example.backend.dto.itemMaterial.ItemMaterialDto;
import com.example.backend.mapper.ItemMaterialMapper;
import com.example.backend.model.ItemMaterial;
import com.example.backend.service.ItemMaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items/{itemId}/materials")
public class ItemMaterialController {

    private final ItemMaterialService service;
    private final ItemMaterialMapper mapper;

    public ItemMaterialController(ItemMaterialService service, ItemMaterialMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ItemMaterialDto>> findByItemId(@PathVariable Long itemId) {
        return ResponseEntity.ok(mapper.toDto(service.findByItemId(itemId)));
    }

    @PutMapping
    public ResponseEntity<ItemMaterialDto> save(@PathVariable Long itemId, @RequestBody ItemMaterialDto dto) {
        ItemMaterial savedItemMaterial = service.save(mapper.fromDto(itemId, dto));
        return ResponseEntity.ok(mapper.toDto(savedItemMaterial));
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable Long itemId, @PathVariable Long resourceId) {
        service.delete(itemId, resourceId);
        return ResponseEntity.noContent().build();
    }
}
