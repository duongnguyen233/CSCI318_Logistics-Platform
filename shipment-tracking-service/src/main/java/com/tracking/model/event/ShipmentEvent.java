package com.tracking.model.event;
import java.time.LocalDateTime;

public class ShipmentEvent {
    private Long shipmentId;
    private String trackingNumber;
    private String action;  // CREATED, UPDATED, DELETED
    private LocalDateTime timestamp;

    public ShipmentEvent() {}

    public ShipmentEvent(Long shipmentId, String trackingNumber, String action) {
        this.shipmentId = shipmentId;
        this.trackingNumber = trackingNumber;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public Long getShipmentId() { return shipmentId; }
    public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "ShipmentEvent{" +
                "shipmentId=" + shipmentId +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", action='" + action + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
