package com.warehouse.controller;

import com.warehouse.model.WarehouseItem;
import com.warehouse.service.WarehouseItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse/items")
public class WarehouseItemController {

    private final WarehouseItemService service;

    public WarehouseItemController(WarehouseItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WarehouseItem> createItem(@RequestBody WarehouseItem item) {
        return ResponseEntity.ok(service.saveItem(item));
    }

    @GetMapping
    public ResponseEntity<List<WarehouseItem>> getAllItems() {
        return ResponseEntity.ok(service.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseItem> getItemById(@PathVariable Long id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseItem> updateItem(@PathVariable Long id, @RequestBody WarehouseItem item) {
        return ResponseEntity.ok(service.updateItem(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}