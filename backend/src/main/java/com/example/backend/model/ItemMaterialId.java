package com.example.backend.model;

public class ItemMaterialId {

    private Long itemId;
    private Long resourceId;

    public ItemMaterialId() {
    }

    public ItemMaterialId(Long itemId, Long resourceId) {
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
