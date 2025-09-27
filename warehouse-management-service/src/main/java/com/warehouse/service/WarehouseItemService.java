package com.warehouse.service;

import com.warehouse.model.WarehouseItem;
import com.warehouse.model.event.WarehouseItemEvent;
import com.warehouse.repository.WarehouseItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseItemService {

    private final WarehouseItemRepository repository;

    public WarehouseItemService(WarehouseItemRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public WarehouseItem saveItem(WarehouseItem item) {
        WarehouseItem saved = repository.save(item);

        // Create event
        WarehouseItemEvent event = new WarehouseItemEvent(
                saved.getId(),
                saved.getName(),
                "CREATED"
        );
        System.out.println("EVENT: " + event);

        return saved;
    }

    // READ - all items
    public List<WarehouseItem> getAllItems() {
        return repository.findAll();
    }

    // READ - single item
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

                    // Create event
                    WarehouseItemEvent event = new WarehouseItemEvent(
                            saved.getId(),
                            saved.getName(),
                            "UPDATED"
                    );
                    System.out.println("EVENT: " + event);

                    return saved;
                })
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // DELETE
    public void deleteItem(Long id) {
        repository.deleteById(id);

        // Create event
        WarehouseItemEvent event = new WarehouseItemEvent(
                id,
                "Unknown",   // name not available after deletion
                "DELETED"
        );
        System.out.println("EVENT: " + event);
    }
}
