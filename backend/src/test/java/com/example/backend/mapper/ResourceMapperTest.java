package com.example.backend.mapper;

import com.example.backend.dto.resource.ResourceDto;
import com.example.backend.dto.resource.ResourceInputDto;
import com.example.backend.model.Resource;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceMapperTest {

    private final ResourceMapper mapper = new ResourceMapper();

    @Test
    void givenResource_whenToDto_thenReturnResourceDto() {
        Resource resource = new Resource(1L, "Resource");

        ResourceDto result = mapper.toDto(resource);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("Resource");
    }

    @Test
    void givenResourceInputDto_whenFromDto_thenReturnResource() {
        ResourceInputDto dto = new ResourceInputDto("Resource");

        Resource result = mapper.fromDto(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNull();
        assertThat(result.getName()).isEqualTo("Resource");
    }

    @Test
    void givenListOfResources_whenToDto_thenReturnListOfResourceDtos() {
        Resource resource1 = new Resource(1L, "Resource 1");
        Resource resource2 = new Resource(2L, "Resource 2");

        List<Resource> resources = List.of(resource1, resource2);

        List<ResourceDto> result = mapper.toDto(resources);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).id()).isEqualTo(1L);
        assertThat(result.get(1).id()).isEqualTo(2L);
        assertThat(result.get(0).name()).isEqualTo("Resource 1");
        assertThat(result.get(1).name()).isEqualTo("Resource 2");
    }
}
