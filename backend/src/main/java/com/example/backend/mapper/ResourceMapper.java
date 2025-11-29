package com.example.backend.mapper;

import com.example.backend.dto.resource.ResourceDto;
import com.example.backend.dto.resource.ResourceInputDto;
import com.example.backend.model.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceMapper {

    public List<ResourceDto> toDto(List<Resource> models) {
        return models.stream().map(this::toDto).toList();
    }

    public ResourceDto toDto(Resource model) {
        return new ResourceDto(model.getId(), model.getName());
    }

    public Resource fromDto(ResourceInputDto dto) {
        return new Resource(dto.name());
    }
}
