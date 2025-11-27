package com.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_material")
@IdClass(ItemMaterialId.class)
public class ItemMaterial {

    @Id
    private Long itemId;

    @Id
    private Long resourceId;

    @Column(nullable = false)
    private long amount;

    public ItemMaterial() {
    }

    public ItemMaterial(Long itemId, Long resourceId, long amount) {
        this.itemId = itemId;
        this.resourceId = resourceId;
        this.amount = amount;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
