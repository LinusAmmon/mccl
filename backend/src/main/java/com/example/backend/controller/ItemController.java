package com.example.backend.controller;

import com.example.backend.dto.item.ItemDto;
import com.example.backend.dto.item.ItemInputDto;
import com.example.backend.mapper.ItemMapper;
import com.example.backend.model.Item;
import com.example.backend.service.ItemService;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(mapper.toDto(service.findById(itemId)));
    }

    @PostMapping
    public ResponseEntity<ItemDto> create(@RequestBody ItemInputDto item) {
        Item createdItem = service.create(mapper.fromDto(item));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(createdItem));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemDto> update(@PathVariable Long itemId, @RequestBody ItemInputDto item) {
        Item updatedItem = service.update(itemId, mapper.fromDto(item));
        return ResponseEntity.ok(mapper.toDto(updatedItem));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable Long itemId) {
        service.delete(itemId);
        return ResponseEntity.noContent().build();
    }
}
