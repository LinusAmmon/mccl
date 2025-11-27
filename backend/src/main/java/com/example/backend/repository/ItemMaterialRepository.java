package com.example.backend.repository;

import com.example.backend.model.ItemMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemMaterialRepository extends JpaRepository<ItemMaterial, Long> {
    List<ItemMaterial> findByItemId(Long itemId);
    void deleteByItemId(Long itemId);
    void deleteByResourceId(Long resourceId);
}
