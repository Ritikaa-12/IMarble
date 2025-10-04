package com.marble.controllers;

import com.marble.dto.OrderItemDto1;
import com.marble.service.OrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    @Autowired
    private OrderitemService orderitemService;

    @PostMapping("/create")
    public ResponseEntity<OrderItemDto1> createOrderItem(@RequestBody OrderItemDto1 orderItemDto) {
        return new ResponseEntity<>(orderitemService.createOrderItem(orderItemDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto1> getOrderItemById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderitemService.getOrderItemById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderItemDto1>> getAllOrderItems() {
        return ResponseEntity.ok(orderitemService.getAllOrderItems());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto1> updateOrderItem(
            @PathVariable Integer id,
            @RequestBody OrderItemDto1 orderItemDto) {
        return ResponseEntity.ok(orderitemService.updateOrderItem(id, orderItemDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Integer id) {
        orderitemService.deleteOrderItem(id);
        return ResponseEntity.ok("Order Item deleted successfully with ID: " + id);
    }

}

