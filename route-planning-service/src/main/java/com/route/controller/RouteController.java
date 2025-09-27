package com.route.controller;

import com.route.model.RouteResponse;
import com.route.service.DijkstraAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final DijkstraAlgorithm dijkstra;
    private final RestTemplate restTemplate;

    public RouteController(RestTemplate restTemplate) {
        this.dijkstra = new DijkstraAlgorithm();
        this.restTemplate = restTemplate;
    }

    @GetMapping("/plan")
    public ResponseEntity<RouteResponse> planRoute() {
        // Call shipment-tracking-service
        String url = "http://localhost:8083/shipments";
        ShipmentDTO[] shipments = restTemplate.getForObject(url, ShipmentDTO[].class);

        // Filter only Pending shipments, collect addresses
        List<String> addresses = Arrays.stream(shipments)
                .filter(s -> "Pending".equalsIgnoreCase(s.getStatus()))
                .map(ShipmentDTO::getAddress)
                .collect(Collectors.toList());

        // Run dummy Dijkstra
        List<String> optimized = dijkstra.findOptimalRoute(addresses);

        return ResponseEntity.ok(new RouteResponse(optimized));
    }

    // Inner DTO class to deserialize shipment response
    static class ShipmentDTO {
        private String trackingNumber;
        private String status;
        private String destination;
        private String address;

        public String getTrackingNumber() { return trackingNumber; }
        public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getDestination() { return destination; }
        public void setDestination(String destination) { this.destination = destination; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
    }
}
