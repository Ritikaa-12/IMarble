package com.marble.controllers;

import com.marble.dto.OrderItemDto1;
import com.marble.service.OrderitemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    @Autowired
    private OrderitemService orderitemService;

    @PreAuthorize("hasAnyRole('RECEPTIONIST', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<OrderItemDto1> createOrderItem(@RequestBody OrderItemDto1 orderItemDto) {
        return new ResponseEntity<>(orderitemService.createOrderItem(orderItemDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'DISPATCHER', 'ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<OrderItemDto1>> getAllOrderItems() {
        return ResponseEntity.ok(orderitemService.getAllOrderItems());
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'MANAGER', 'DISPATCHER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto1> getOrderItemById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderitemService.getOrderItemById(id));
    }

    @PreAuthorize("hasAnyRole('RECEPTIONIST', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto1> updateOrderItem(
            @PathVariable Integer id,
            @RequestBody OrderItemDto1 orderItemDto) {
        return ResponseEntity.ok(orderitemService.updateOrderItem(id, orderItemDto));
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/myorders")
    public ResponseEntity<List<OrderItemDto1>> getMyOrders(Authentication authentication) {
        String email = authentication.getName();
        List<OrderItemDto1> myOrders = orderitemService.getOrderItemsByClientEmail(email);
        return ResponseEntity.ok(myOrders);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Integer id) {
        orderitemService.deleteOrderItem(id);
        return ResponseEntity.ok("Order Item deleted successfully with ID: " + id);
    }
}