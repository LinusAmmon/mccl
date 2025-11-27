package com.example.backend.repository;

import com.example.backend.model.ItemMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemMaterialRepository extends JpaRepository<ItemMaterial, UUID> {
    List<ItemMaterial> findByItemId(UUID itemId);
    void deleteByItemId(UUID itemId);
    void deleteByResourceId(UUID resourceId);
}
