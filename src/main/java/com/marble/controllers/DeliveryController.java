package com.marble.controllers;
import com.marble.dto.DeliveryDto;
import com.marble.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/")
    public ResponseEntity<DeliveryDto> createDelivery(@RequestBody DeliveryDto deliveryDto) {
        DeliveryDto createdDelivery = deliveryService.createDelivery(deliveryDto);
        return new ResponseEntity<>(createdDelivery, HttpStatus.CREATED);
    }

    
    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryDto> getDeliveryById(@PathVariable Integer deliveryId) {
        DeliveryDto deliveryDto = deliveryService.getDeliveryById(deliveryId);
        return ResponseEntity.ok(deliveryDto);
    }

    
    @GetMapping("/by-staff/{staffId}")
    public ResponseEntity<List<DeliveryDto>> getDeliveriesByStaff(@PathVariable Integer staffId) {
        List<DeliveryDto> deliveries = deliveryService.getDeliveriesByStaff(staffId);
        return ResponseEntity.ok(deliveries);
    }

    
    @PutMapping("/{deliveryId}/assign-staff/{staffId}")
    public ResponseEntity<DeliveryDto> assignStaff(@PathVariable Integer deliveryId, @PathVariable Integer staffId) {
        DeliveryDto updatedDelivery = deliveryService.assignStaff(deliveryId, staffId);
        return ResponseEntity.ok(updatedDelivery);
    }

    
    @PutMapping("/{deliveryId}/status")
    public ResponseEntity<DeliveryDto> updateStatus(@PathVariable Integer deliveryId, @RequestParam String status) {
        DeliveryDto updatedDelivery = deliveryService.updateStatus(deliveryId, status);
        return ResponseEntity.ok(updatedDelivery);
    }

    
    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<String> deleteDelivery(@PathVariable Integer deliveryId) {
        deliveryService.deleteDeliveryId(deliveryId);
        return ResponseEntity.ok("Delivery with ID: " + deliveryId + " was deleted successfully.");
    }
}