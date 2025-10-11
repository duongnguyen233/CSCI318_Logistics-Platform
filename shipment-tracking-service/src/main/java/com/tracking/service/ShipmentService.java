package com.tracking.service;

import com.tracking.model.Shipment;
import com.tracking.model.event.ShipmentEvent;
import com.tracking.repository.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    private static final Logger log = LoggerFactory.getLogger(ShipmentService.class);
    private final ShipmentRepository repository;

    public ShipmentService(ShipmentRepository repository) {
        this.repository = repository;
    }

    // ------------------ CRUD ------------------

    public Shipment saveShipment(Shipment shipment) {
        Shipment saved = repository.save(shipment);
        log.info("Event: {}", new ShipmentEvent(saved.getId(), saved.getTrackingNumber(), "CREATED"));
        return saved;
    }

    public List<Shipment> getAllShipments() {
        return repository.findAll();
    }

    public Optional<Shipment> getShipmentById(Long id) {
        return repository.findById(id);
    }

    public Shipment updateShipment(Long id, Shipment updatedShipment) {
        return repository.findById(id)
                .map(s -> {
                    s.setTrackingNumber(updatedShipment.getTrackingNumber());
                    s.setStatus(updatedShipment.getStatus());
                    s.setDestination(updatedShipment.getDestination());
                    s.setAddress(updatedShipment.getAddress());
                    Shipment saved = repository.save(s);
                    log.info("Event: {}", new ShipmentEvent(saved.getId(), saved.getTrackingNumber(), "UPDATED"));
                    return saved;
                })
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
    }

    public void deleteShipment(Long id) {
        repository.deleteById(id);
        log.info("Event: {}", new ShipmentEvent(id, "Unknown", "DELETED"));
    }

    // ------------------ Kafka Listener ------------------

    @KafkaListener(topics = "inventory-events", groupId = "shipment-group")
    public void handleInventoryEvent(WarehouseItemEvent event) {
        log.info("📦 Received inventory event: {}", event);
        if ("CREATED".equalsIgnoreCase(event.getAction())) {
            Shipment autoShipment = new Shipment();
            autoShipment.setTrackingNumber("AUTO-" + event.getItemId());
            autoShipment.setStatus("Pending");
            autoShipment.setDestination("Auto-created for warehouse item");
            autoShipment.setAddress("Processing Hub");
            repository.save(autoShipment);
            log.info("✅ Auto-created shipment for {}", event.getItemName());
        }
    }

    // Inner DTO to deserialize Kafka JSON
    public static class WarehouseItemEvent {
        private Long itemId;
        private String itemName;
        private String action;

        public WarehouseItemEvent() {}
        public WarehouseItemEvent(Long itemId, String itemName, String action) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.action = action;
        }

        public Long getItemId() { return itemId; }
        public String getItemName() { return itemName; }
        public String getAction() { return action; }

        @Override
        public String toString() {
            return "WarehouseItemEvent{" +
                    "itemId=" + itemId +
                    ", itemName='" + itemName + '\'' +
                    ", action='" + action + '\'' +
                    '}';
        }
    }
}
