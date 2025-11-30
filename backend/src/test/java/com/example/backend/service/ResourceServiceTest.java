package com.example.backend.service;

import com.example.backend.model.Resource;
import com.example.backend.repository.ItemMaterialRepository;
import com.example.backend.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResourceServiceTest {

    private Resource resource;

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private ItemMaterialRepository itemMaterialRepository;

    @InjectMocks
    private ResourceService resourceService;

    @BeforeEach
    void setUp() {
        resource = new Resource();
    }

    @Test
    void givenMultipleResourcesInRepository_whenFindAll_thenAllResourcesAreReturned() {
        when(resourceRepository.findAll()).thenReturn(List.of(resource, resource));

        List<Resource> result = resourceService.findAll();

        assertThat(result).isEqualTo(List.of(resource, resource));

        verify(resourceRepository).findAll();
    }

    @Test
    void givenEmptyRepository_whenFindAll_thenEmptyListIsReturned() {
        when(resourceRepository.findAll()).thenReturn(List.of());

        List<Resource> result = resourceService.findAll();

        assertThat(result).isEmpty();

        verify(resourceRepository).findAll();
    }

    @Test
    void givenExistingResourceId_whenFindById_thenResourceIsReturned() {
        when(resourceRepository.findById(1L)).thenReturn(Optional.of(resource));

        Resource result = resourceService.findById(1L);

        assertThat(result).isEqualTo(resource);

        verify(resourceRepository).findById(1L);
    }

    @Test
    void givenNonExistingResourceId_whenFindById_thenThrowsException() {
        when(resourceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> resourceService.findById(1L))
                .isInstanceOf(ResponseStatusException.class);

        verify(resourceRepository).findById(1L);
    }

    @Test
    void givenValidResource_whenCreate_thenReturnSavedResource() {
        when(resourceRepository.save(resource)).thenReturn(resource);

        Resource result = resourceService.create(resource);

        assertThat(result).isEqualTo(resource);

        verify(resourceRepository).save(resource);
    }

    @Test
    void givenExistingResourceIdAndValidResource_whenUpdate_thenResourceIsUpdated() {
        when(resourceRepository.existsById(1L)).thenReturn(true);
        when(resourceRepository.save(resource)).thenReturn(resource);

        Resource result = resourceService.update(1L, resource);

        assertThat(result).isEqualTo(resource);
        assertThat(result.getId()).isEqualTo(1L);

        verify(resourceRepository).existsById(1L);
        verify(resourceRepository).save(resource);
    }

    @Test
    void givenNonExistingResourceId_whenUpdate_thenThrowsException() {
        when(resourceRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> resourceService.update(1L, resource))
                .isInstanceOf(ResponseStatusException.class);

        verify(resourceRepository).existsById(1L);
        verify(resourceRepository, never()).save(resource);
    }

    @Test
    void givenExistingResourceId_whenDelete_thenResourceAndRelationsAreDeleted() {
        when(resourceRepository.findById(1L)).thenReturn(Optional.of(resource));

        resourceService.delete(1L);

        verify(resourceRepository).findById(1L);
        verify(resourceRepository).deleteById(1L);
        verify(itemMaterialRepository).deleteByResourceId(1L);
    }

    @Test
    void givenNonExistingResourceId_whenDelete_thenThrowsException() {
        when(resourceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> resourceService.delete(1L))
                .isInstanceOf(ResponseStatusException.class);

        verify(resourceRepository).findById(1L);
        verify(resourceRepository, never()).deleteById(1L);
        verify(itemMaterialRepository, never()).deleteByResourceId(1L);
    }
}
