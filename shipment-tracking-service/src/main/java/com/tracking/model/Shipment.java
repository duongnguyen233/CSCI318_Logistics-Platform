package com.tracking.model;

import jakarta.persistence.*;

@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingNumber;
    private String status;      // Pending, In Transit, Delivered
    private String destination;
    private String address;

    private Long itemId;        // NEW: Reference to WarehouseItem

    public Shipment() {}

    public Shipment(String trackingNumber, String status, String destination, String address, Long itemId) {
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.destination = destination;
        this.address = address;
        this.itemId = itemId;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", status='" + status + '\'' +
                ", destination='" + destination + '\'' +
                ", address='" + address + '\'' +
                ", itemId=" + itemId +
                '}';
    }
}
