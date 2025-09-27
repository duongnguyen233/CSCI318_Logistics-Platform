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
    private String address;     // NEW: address for route planning

    public Shipment() {}

    public Shipment(String trackingNumber, String status, String destination, String address) {
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.destination = destination;
        this.address = address;
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

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", status='" + status + '\'' +
                ", destination='" + destination + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
