package com.marble.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.marble.dto.OrderrDto;
import com.marble.service.OrderrService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderrController {

    @Autowired
    private OrderrService orderrService;

    // Create order: Receptionist, Admin
    @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
    @PostMapping("/create")
    public ResponseEntity<OrderrDto> createOrder(@RequestBody OrderrDto orderrDto) {
        return new ResponseEntity<>(orderrService.createOrder(orderrDto), HttpStatus.CREATED);
    }

    // Get order by ID: Admin, Receptionist (for update), Dispatcher (for delivery), Client (own orders)
    @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST','DISPATCHER','CLIENT')")
    @GetMapping("/get/{id}")
    public ResponseEntity<OrderrDto> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderrService.getOrderById(id));
    }

    // Get all orders: Manager, Admin
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<OrderrDto>> getAllOrders() {
        return ResponseEntity.ok(orderrService.getAllOrders());
    }

    // Update order: Receptionist, Admin
    @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST')")
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderrDto> updateOrder(@PathVariable Integer id, @RequestBody OrderrDto orderrDto) {
        return ResponseEntity.ok(orderrService.updateOrder(id, orderrDto));
    }

    // Delete order: Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        orderrService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully with ID: " + id);
    }
}
