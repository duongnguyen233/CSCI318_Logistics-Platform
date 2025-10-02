# Logistics Microservices Platform 🚚📦🤖

This project implements a logistics management platform using **Spring Boot microservices**.  
It is based on **Domain-Driven Design (DDD)**, **Event-Driven Architecture with Kafka**, and integrates an **AI Copilot powered by LangChain4j**.  

The following new components and patterns are implemented:

---

### 📦 Warehouse Management Service
- Manages inventory items (`WarehouseItem`) and stock updates.  
- Exposes REST APIs for CRUD operations on items.  
- Publishes **InventoryUpdated events** when stock changes.  
- Example Entity:  
  ```java
  @Entity
  public class WarehouseItem {
      @Id @GeneratedValue
      private Long id;
      private String name;
      private int quantity;
      private boolean perishable;
  }
  ```

---

### 🚚 Shipment Tracking Service
- Manages shipments (`Shipment`) and delivery status.  
- Tracks **trackingNumber, status, destination, address, itemId**.  
- Exposes REST APIs to create, update, and query shipments.  
- Consumes **InventoryUpdated events** and publishes **ShipmentCreated events**.  
- Example API:
  ```http
  POST /shipments
  {
    "trackingNumber": "TRK2001",
    "status": "Pending",
    "destination": "Melbourne",
    "address": "120 Collins St, VIC",
    "itemId": 3
  }
  ```

---

### 🗺️ Route Planning & Dispatch Service
- Optimizes delivery routes and courier assignments.  
- Consumes **ShipmentCreated events** to assign couriers.  
- Publishes **RouteOptimized & DispatchAssigned events**.  
- Uses **Spring Boot service layer** for business logic.  

---

### 🤖 AI Copilot Service
- Integrates **LangChain4j** with **Google Gemini API**.  
- Provides **natural language queries** for customers and operators.  
- Example:  
  - Input → “Where is my parcel TRK2001?”  
  - Process → AI queries Shipment Service via REST.  
  - Output → “Your parcel is in Sydney Hub, expected delivery in 2 days.”  

---

## 🏗️ Architecture

- **DDD & Bounded Contexts** → Each microservice owns its domain model and database.  
- **Event-Driven with Kafka** → Services communicate asynchronously via domain events.  
- **Stream Processing** → Spring Cloud Stream + Kafka Streams for real-time analytics (e.g., perishable alerts, shipment delays).  
- **AI Integration** → AI Copilot augments system usability with natural language interfaces.  

---

## 🚀 Run & Configuration

### Requirements
- Java 21+  
- Maven  
- Kafka (running locally or in Docker)  
- Postman for API testing  
- Google Gemini API Key (for AI Copilot)  

### Example Startup
1. Start **Kafka** locally.  
2. Run each service:  
   ```bash
   mvn spring-boot:run -pl warehouse-management-service
   mvn spring-boot:run -pl shipment-tracking-service
   mvn spring-boot:run -pl route-planning-service
   mvn spring-boot:run -pl agentAI-service
   ```
3. Access APIs on ports (example):
   - Warehouse → `http://localhost:8081`
   - Shipment → `http://localhost:8082`
   - Route Planning → `http://localhost:8083`
   - AI Copilot → `http://localhost:8086`

---

## 🧪 Example Use Cases

1. **Create a shipment**  
   ```bash
   curl -X POST http://localhost:8082/shipments \
   -H "Content-Type: application/json" \
   -d '{"trackingNumber":"TRK2001","status":"Pending","destination":"Melbourne","address":"120 Collins St, VIC","itemId":3}'
   ```

2. **Get all shipments**  
   ```bash
   curl http://localhost:8082/shipments
   ```

3. **Query AI Copilot** (via Postman)  
   ```http
   POST http://localhost:8086/chat/user123
   {
     "message": "Where is my parcel TRK2001?"
   }
   ```

---

## 📊 Event-Sourcing & Audit Logging
- Kafka topics store immutable event logs:
  - `ShipmentCreated`, `ShipmentDelivered`, `InventoryUpdated`, `RouteOptimized`.  
- These logs support **traceability, replay, debugging, and analytics dashboards**.  

---

## 🔗 Repository
👉 [GitHub Repo](https://github.com/your-org/logistics-microservices)  
