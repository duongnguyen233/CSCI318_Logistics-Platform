package com.tracking.service;

import com.tracking.model.Shipment;
import com.tracking.model.event.ShipmentEvent;
import com.tracking.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    private final ShipmentRepository repository;

    public ShipmentService(ShipmentRepository repository) {
        this.repository = repository;
    }

    public Shipment saveShipment(Shipment shipment) {
        Shipment saved = repository.save(shipment);
        System.out.println(new ShipmentEvent(saved.getId(), saved.getTrackingNumber(), "CREATED"));
        return saved;
    }

    public List<Shipment> getAllShipments() {
        return repository.findAll();
    }

    public Optional<Shipment> getShipmentById(Long id) {
        return repository.findById(id);
    }

    public Shipment updateShipment(Long id, Shipment updatedShipment) {
        return repository.findById(id).map(s -> {
            s.setTrackingNumber(updatedShipment.getTrackingNumber());
            s.setStatus(updatedShipment.getStatus());
            s.setDestination(updatedShipment.getDestination());
            Shipment saved = repository.save(s);
            System.out.println(new ShipmentEvent(saved.getId(), saved.getTrackingNumber(), "UPDATED"));
            return saved;
        }).orElseThrow(() -> new RuntimeException("Shipment not found"));
    }

    public void deleteShipment(Long id) {
        repository.deleteById(id);
        System.out.println(new ShipmentEvent(id, "Unknown", "DELETED"));
    }
}
