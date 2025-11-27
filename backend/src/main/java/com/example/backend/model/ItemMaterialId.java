package com.example.backend.model;

import java.util.UUID;

public class ItemMaterialId {

    private UUID itemId;
    private UUID resourceId;

    public ItemMaterialId() {
    }

    public ItemMaterialId(UUID itemId, UUID resourceId) {
        this.itemId = itemId;
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ItemMaterialId that = (ItemMaterialId) o;
        return itemId.equals(that.itemId) && resourceId.equals(that.resourceId);
    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + resourceId.hashCode();
        return result;
    }
}
