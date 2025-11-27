package com.example.backend.controller;

import com.example.backend.model.Resource;
import com.example.backend.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources")
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Resource>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<Resource> findById(@PathVariable Long resourceId) {
        return service.findById(resourceId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resource> save(@RequestBody Resource resource) {
        return ResponseEntity.ok(service.save(resource));
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long resourceId) {
        return service.deleteById(resourceId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
