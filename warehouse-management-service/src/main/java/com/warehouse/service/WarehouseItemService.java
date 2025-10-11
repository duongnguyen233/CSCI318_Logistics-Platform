package com.warehouse.service;

import com.warehouse.model.WarehouseItem;
import com.warehouse.model.event.WarehouseItemEvent;
import com.warehouse.repository.WarehouseItemRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseItemService {

    private final WarehouseItemRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public WarehouseItemService(WarehouseItemRepository repository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    // CREATE
    public WarehouseItem saveItem(WarehouseItem item) {
        WarehouseItem saved = repository.save(item);

        WarehouseItemEvent event = new WarehouseItemEvent(
                saved.getId(), saved.getName(), "CREATED"
        );
        kafkaTemplate.send("inventory-events", event);

        System.out.println("📤 Sent Kafka Event: " + event);
        return saved;
    }

    // READ
    public List<WarehouseItem> getAllItems() {
        return repository.findAll();
    }

    public Optional<WarehouseItem> getItemById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public WarehouseItem updateItem(Long id, WarehouseItem updatedItem) {
        return repository.findById(id)
                .map(item -> {
                    item.setName(updatedItem.getName());
                    item.setDescription(updatedItem.getDescription());
                    item.setQuantity(updatedItem.getQuantity());
                    item.setLocation(updatedItem.getLocation());
                    WarehouseItem saved = repository.save(item);

                    WarehouseItemEvent event = new WarehouseItemEvent(saved.getId(), saved.getName(), "UPDATED");
                    kafkaTemplate.send("inventory-events", event);
                    System.out.println("📤 Sent Kafka Event: " + event);

                    return saved;
                })
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // DELETE
    public void deleteItem(Long id) {
        repository.deleteById(id);
        WarehouseItemEvent event = new WarehouseItemEvent(id, "Unknown", "DELETED");
        kafkaTemplate.send("inventory-events", event);
        System.out.println("📤 Sent Kafka Event: " + event);
    }
}
