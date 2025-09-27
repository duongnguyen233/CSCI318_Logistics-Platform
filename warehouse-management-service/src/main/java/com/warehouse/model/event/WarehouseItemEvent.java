package com.warehouse.model.event;

import java.time.LocalDateTime;

public class WarehouseItemEvent {

    private Long itemId;
    private String name;
    private String action;     // e.g., CREATED, UPDATED, DELETED
    private LocalDateTime timestamp;

    public WarehouseItemEvent() {}

    public WarehouseItemEvent(Long itemId, String name, String action) {
        this.itemId = itemId;
        this.name = name;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WarehouseItemEvent{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", action='" + action + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
