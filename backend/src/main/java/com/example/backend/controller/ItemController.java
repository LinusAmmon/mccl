package com.example.backend.controller;

import com.example.backend.dto.item.ItemDto;
import com.example.backend.dto.item.ItemInputDto;
import com.example.backend.mapper.ItemMapper;
import com.example.backend.model.Item;
import com.example.backend.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService service;
    private final ItemMapper mapper;

    public ItemController(ItemService service, ItemMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> findAll() {
        return ResponseEntity.ok(mapper.toDto(service.findAll()));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> findById(@PathVariable Long itemId) {
        return service.findById(itemId)
                .map(item -> ResponseEntity.ok(mapper.toDto(item)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemDto> save(@RequestBody ItemInputDto item) {
        Item savedItem = service.save(mapper.fromDto(item));
        return ResponseEntity.ok(mapper.toDto(savedItem));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long itemId) {
        return service.deleteById(itemId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
