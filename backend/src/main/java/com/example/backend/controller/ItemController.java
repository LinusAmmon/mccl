package com.example.backend.controller;

import com.example.backend.model.Item;
import com.example.backend.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> findById(@PathVariable Long itemId) {
        return service.findById(itemId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Item> save(@RequestBody Item item) {
        return ResponseEntity.ok(service.save(item));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long itemId) {
        return service.deleteById(itemId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
