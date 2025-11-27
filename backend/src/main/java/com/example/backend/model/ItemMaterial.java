package com.example.backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "item_material")
@IdClass(ItemMaterialId.class)
public class ItemMaterial {

    @Id
    private UUID itemId;

    @Id
    private UUID resourceId;

    @Column(nullable = false)
    private long amount;

    public ItemMaterial() {
    }

    public ItemMaterial(UUID itemId, UUID resourceId, long amount) {
        this.itemId = itemId;
        this.resourceId = resourceId;
        this.amount = amount;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public UUID getResourceId() {
        return resourceId;
    }

    public void setResourceId(UUID resourceId) {
        this.resourceId = resourceId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
