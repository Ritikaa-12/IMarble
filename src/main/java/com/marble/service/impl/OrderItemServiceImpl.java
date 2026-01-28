package com.marble.service.impl;

import com.marble.dto.OrderItemDto1;
import com.marble.entities.OrderItem;
import com.marble.entities.Orderr;
import com.marble.entities.Product;
import com.marble.repos.OrderItemRepository;
import com.marble.repos.OrderrRepository;
import com.marble.repos.ProductRepository;
import com.marble.service.OrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderitemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderrRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // ------------------- DTO <-> Entity -------------------
    private OrderItemDto1 mapToDto(OrderItem orderItem) {
        OrderItemDto1 dto = new OrderItemDto1();
        dto.setOrderItemId(orderItem.getOrderItemId());
        dto.setOrderId(orderItem.getOrder().getOrderId());
        dto.setProductId(orderItem.getProduct().getProductId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setDiscount(orderItem.getDiscount());
        dto.setTax(orderItem.getTax());
        dto.setPrice(orderItem.getPrice());
        dto.setSubTotal(orderItem.getSubTotal());
        return dto;
    }

    private OrderItem mapToEntity(OrderItemDto1 dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(dto.getOrderItemId());

        Orderr order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with id " + dto.getOrderId()));
        orderItem.setOrder(order);

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id " + dto.getProductId()));
        orderItem.setProduct(product);

        orderItem.setQuantity(dto.getQuantity());
        orderItem.setDiscount(dto.getDiscount());
        orderItem.setTax(dto.getTax());
        orderItem.setPrice(dto.getPrice());
        orderItem.setSubTotal(dto.getSubTotal());
        return orderItem;
    }

    // ------------------- Service Methods -------------------

    @Override
    public OrderItemDto1 createOrderItem(OrderItemDto1 orderItemDto) {
        OrderItem orderItem = mapToEntity(orderItemDto);
        OrderItem saved = orderItemRepository.save(orderItem);
        return mapToDto(saved);
    }

    @Override
    public OrderItemDto1 getOrderItemById(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order Item not found with id " + orderItemId));
        return mapToDto(orderItem);
    }

    @Override
    public List<OrderItemDto1> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDto1 updateOrderItem(Integer orderItemId, OrderItemDto1 orderItemDto) {
        OrderItem existing = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order Item not found with id " + orderItemId));

        existing.setQuantity(orderItemDto.getQuantity());
        existing.setDiscount(orderItemDto.getDiscount());
        existing.setTax(orderItemDto.getTax());
        existing.setPrice(orderItemDto.getPrice());
        existing.setSubTotal(orderItemDto.getSubTotal());

        // update order & product references if changed
        if (!existing.getOrder().getOrderId().equals(orderItemDto.getOrderId())) {
            Orderr order = orderRepository.findById(orderItemDto.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found with id " + orderItemDto.getOrderId()));
            existing.setOrder(order);
        }

        if (!existing.getProduct().getProductId().equals(orderItemDto.getProductId())) {
            Product product = productRepository.findById(orderItemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id " + orderItemDto.getProductId()));
            existing.setProduct(product);
        }

        OrderItem updated = orderItemRepository.save(existing);
        return mapToDto(updated);
    }
    
    @Override
    public List<OrderItemDto1> getOrderItemsByClientEmail(String email) {
        List<OrderItem> orderItems = orderItemRepository.findByOrder_Client_Email(email);
        return orderItems.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }




    @Override
    public void deleteOrderItem(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order Item not found with id " + orderItemId));
        orderItemRepository.delete(orderItem);
    }
}
