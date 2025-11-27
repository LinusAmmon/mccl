package com.example.backend.service;

import com.example.backend.model.Resource;
import com.example.backend.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> findAll() {
        return resourceRepository.findAll();
    }

    public Optional<Resource> findById(UUID resourceId) {
        return resourceRepository.findById(resourceId);
    }

    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    public boolean deleteById(UUID resourceId) {
        if (findById(resourceId).isEmpty()) {
            return false;
        }
        resourceRepository.deleteById(resourceId);
        return true;
    }
}
