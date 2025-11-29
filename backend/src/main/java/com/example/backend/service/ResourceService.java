package com.example.backend.service;

import com.example.backend.model.Resource;
import com.example.backend.repository.ItemMaterialRepository;
import com.example.backend.repository.ResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ItemMaterialRepository itemMaterialRepository;

    public ResourceService(ResourceRepository resourceRepository, ItemMaterialRepository itemMaterialRepository) {
        this.resourceRepository = resourceRepository;
        this.itemMaterialRepository = itemMaterialRepository;
    }

    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    public Resource findById(Long id) {
        return resourceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Resource create(Resource resource) {
        return resourceRepository.save(resource);
    }

    public Resource update(Long id, Resource resource) {
        if (resourceRepository.existsById(id)) {
            resource.setId(id);
            return resourceRepository.save(resource);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (resourceRepository.findById(id).isPresent()) {
            resourceRepository.deleteById(id);
            itemMaterialRepository.deleteByResourceId(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
