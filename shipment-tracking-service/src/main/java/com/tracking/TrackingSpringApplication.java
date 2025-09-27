package com.tracking;

import com.tracking.model.Shipment;
import com.tracking.repository.ShipmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrackingSpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(TrackingSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadDatabase(ShipmentRepository repository) {
        return args -> {
            String[] addresses = {
                    "1 Martin Place, Sydney NSW 2000",
                    "200 George Street, Sydney NSW 2000",
                    "500 Elizabeth Street, Surry Hills NSW 2010",
                    "1 Macquarie Street, Sydney NSW 2000",
                    "88 Phillip Street, Sydney NSW 2000",
                    "10 Spring Street, Sydney NSW 2000",
                    "100 William Street, Woolloomooloo NSW 2011",
                    "77 King Street, Sydney NSW 2000",
                    "48 Druitt Street, Sydney NSW 2000",
                    "60 Margaret Street, Sydney NSW 2000",
                    "8 York Street, Sydney NSW 2000",
                    "44 Market Street, Sydney NSW 2000",
                    "23 O’Connell Street, Sydney NSW 2000",
                    "2 Park Street, Sydney NSW 2000",
                    "6–8 Underwood Street, Sydney NSW 2000",
                    "45 Clarence Street, Sydney NSW 2000",
                    "201 Kent Street, Sydney NSW 2000",
                    "580 George Street, Sydney NSW 2000",
                    "20 Bond Street, Sydney NSW 2000",
                    "32 York Street, Sydney NSW 2000"
            };

            for (int i = 0; i < addresses.length; i++) {
                String trackingNumber = "TRK" + (1000 + i + 1);
                String status = (i % 2 == 0) ? "Pending" : "In Transit";
                String destination = "Destination " + (i + 1);

                Shipment shipment = new Shipment(
                        trackingNumber,
                        status,
                        destination,
                        addresses[i]
                );

                repository.save(shipment);
            }

            System.out.println("Loaded shipments: " + repository.findAll().size());
        };
    }
}
