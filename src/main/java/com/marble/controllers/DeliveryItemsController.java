package com.marble.controllers;

import com.marble.dto.DeliveryItemsDto;
import com.marble.service.DeliveryItemsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/delivery-items")
public class DeliveryItemsController {

    private final DeliveryItemsService deliveryItemsService;

    public DeliveryItemsController(DeliveryItemsService deliveryItemsService) {
        this.deliveryItemsService = deliveryItemsService;
    }

  
    @PostMapping("/")
    public ResponseEntity<DeliveryItemsDto> addItemToDelivery(@RequestBody DeliveryItemsDto itemDto) {
        DeliveryItemsDto newItem = deliveryItemsService.addItemToDelivery(itemDto);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

  
    @GetMapping("/by-delivery/{deliveryId}")
    public ResponseEntity<List<DeliveryItemsDto>> getItemsForDelivery(@PathVariable Integer deliveryId) {
        List<DeliveryItemsDto> items = deliveryItemsService.getItemsForDelivery(deliveryId);
        return ResponseEntity.ok(items);
    }

  
    @GetMapping("/")
    public ResponseEntity<List<DeliveryItemsDto>> getAllDeliveryItems() {
        List<DeliveryItemsDto> allItems = deliveryItemsService.getAllDeliveryItems();
        return ResponseEntity.ok(allItems);
    }
    
  
    @PutMapping("/{deliverItemId}")
    public ResponseEntity<DeliveryItemsDto> updateItemInDelivery(@PathVariable Integer deliverItemId, @RequestBody DeliveryItemsDto itemDto) {
        DeliveryItemsDto updatedItem = deliveryItemsService.updateItemInDelivery(deliverItemId, itemDto);
        return ResponseEntity.ok(updatedItem);
    }

  
    @DeleteMapping("/{deliverItemId}")
    public ResponseEntity<String> removeItemFromDelivery(@PathVariable Integer deliverItemId) {
        deliveryItemsService.removeItemFromDelivery(deliverItemId);
        return ResponseEntity.ok("Delivery item with ID: " + deliverItemId + " was deleted successfully.");
    }
}