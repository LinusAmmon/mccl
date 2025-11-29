package com.example.backend.controller;

import com.example.backend.dto.resource.ResourceDto;
import com.example.backend.dto.resource.ResourceInputDto;
import com.example.backend.mapper.ResourceMapper;
import com.example.backend.model.Resource;
import com.example.backend.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources")
public class ResourceController {

    private final ResourceService service;
    private final ResourceMapper mapper;

    public ResourceController(ResourceService service, ResourceMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ResourceDto>> findAll() {
        return ResponseEntity.ok(mapper.toDto(service.findAll()));
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<ResourceDto> findById(@PathVariable Long resourceId) {
        return ResponseEntity.ok(mapper.toDto(service.findById(resourceId)));
    }

    @PostMapping
    public ResponseEntity<ResourceDto> create(@RequestBody ResourceInputDto resourceInputDto) {
        Resource createdResource = service.create(mapper.fromDto(resourceInputDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(createdResource));
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<ResourceDto> update(@PathVariable Long resourceId, @RequestBody ResourceInputDto resourceInputDto) {
        Resource updatedResource = service.update(resourceId, mapper.fromDto(resourceInputDto));
        return ResponseEntity.ok(mapper.toDto(updatedResource));
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable Long resourceId) {
        service.delete(resourceId);
        return ResponseEntity.noContent().build();
    }
}
