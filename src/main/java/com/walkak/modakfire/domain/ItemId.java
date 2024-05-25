package com.walkak.modakfire.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class ItemId implements Serializable {
    private Long marketId;
    private Long itemId;

    // Default constructor, equals, and hashCode methods...

    public ItemId() {}

    public ItemId(Long marketId, Long itemId) {
        this.marketId = marketId;
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemId itemId = (ItemId) o;
        return Objects.equals(marketId, itemId.marketId) &&
                Objects.equals(itemId, itemId.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marketId, itemId);
    }

    // Getters and setters...
}
