package com.warehouse;

import com.warehouse.model.WarehouseItem;
import com.warehouse.repository.WarehouseItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WarehouseSpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(WarehouseSpringApplication.class, args);
    }

    // Preload some items into the database for testing
    @Bean
    public CommandLineRunner loadDatabase(WarehouseItemRepository repository) {
        return args -> {
            WarehouseItem item1 = new WarehouseItem("Laptop", "Dell Latitude 5520", 50, "A1");
            repository.save(item1);
            System.out.println("Saved: " + item1);

            WarehouseItem item2 = new WarehouseItem("Monitor", "Dell 24-inch FHD", 120, "B2");
            repository.save(item2);
            System.out.println("Saved: " + item2);

            WarehouseItem item3 = new WarehouseItem("Keyboard", "Logitech Mechanical", 200, "C3");
            repository.save(item3);
            System.out.println("Saved: " + item3);
        };
    }
}
