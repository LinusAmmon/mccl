package com.example.backend.service;

import com.example.backend.model.Resource;
import com.example.backend.repository.ItemMaterialRepository;
import com.example.backend.repository.ResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ItemMaterialRepository itemMaterialRepository;

    public ResourceService(ResourceRepository resourceRepository,
                           ItemMaterialRepository itemMaterialRepository) {
        this.resourceRepository = resourceRepository;
        this.itemMaterialRepository = itemMaterialRepository;
    }

    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    public Optional<Resource> findById(Long resourceId) {
        return resourceRepository.findById(resourceId);
    }

    public Resource create(Resource resource) {
        return resourceRepository.save(resource);
    }

    public Resource update(Long resourceId, Resource resource) {
        if (resourceRepository.existsById(resourceId)) {
            resource.setId(resourceId);
            return resourceRepository.save(resource);
        } else {
            throw new RuntimeException("Resource not found");
        }
    }

    @Transactional
    public boolean deleteById(Long resourceId) {
        if (findById(resourceId).isEmpty()) {
            return false;
        }
        itemMaterialRepository.deleteByResourceId(resourceId);
        resourceRepository.deleteById(resourceId);
        return true;
    }
}
