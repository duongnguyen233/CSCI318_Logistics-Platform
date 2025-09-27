package com.warehouse.repository;

import com.warehouse.model.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Long> {
}