package com.marble.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marble.dto.OrderrDto;
import com.marble.entities.Orderr;
import com.marble.entities.Client;
import com.marble.entities.Shop;
import com.marble.repos.OrderrRepository;
import com.marble.repos.ClientRepository;
import com.marble.repos.ShopRepository;
import com.marble.service.OrderrService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderrServiceImpl implements OrderrService {

    @Autowired
    private OrderrRepository orderrRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ShopRepository shopRepository;

    // Convert Entity → DTO
    private OrderrDto mapToDto(Orderr orderr) {
        OrderrDto dto = new OrderrDto();

        dto.setOrderId(orderr.getOrderId());
        dto.setClientId(orderr.getClient() != null ? orderr.getClient().getClientId() : null);
        dto.setShopId(orderr.getShop() != null ? orderr.getShop().getShopId() : null);
        dto.setOrderNo(orderr.getOrderNo());
        dto.setItems(orderr.getItems());
        dto.setStatus(orderr.getStatus());
        dto.setTotalAmount(orderr.getTotalAmount());

        return dto;
    }

    // Convert DTO → Entity
    private Orderr mapToEntity(OrderrDto dto) {
        Orderr orderr = new Orderr();

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + dto.getClientId()));
        Shop shop = shopRepository.findById(dto.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found with ID: " + dto.getShopId()));

        orderr.setClient(client);
        orderr.setShop(shop);
        orderr.setOrderNo(dto.getOrderNo());
        orderr.setItems(dto.getItems());
        orderr.setStatus(dto.getStatus());
        orderr.setTotalAmount(dto.getTotalAmount());

        return orderr;
    }

    @Override
    public OrderrDto createOrder(OrderrDto orderrDto) {
        Orderr orderr = mapToEntity(orderrDto);
        Orderr savedOrderr = orderrRepository.save(orderr);
        return mapToDto(savedOrderr);
    }

    @Override
    public OrderrDto getOrderById(Integer orderId) {
        Orderr orderr = orderrRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        return mapToDto(orderr);
    }

    @Override
    public List<OrderrDto> getAllOrders() {
        return orderrRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderrDto updateOrder(Integer orderId, OrderrDto orderrDto) {
        Orderr existingOrder = orderrRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        Client client = clientRepository.findById(orderrDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + orderrDto.getClientId()));
        Shop shop = shopRepository.findById(orderrDto.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found with ID: " + orderrDto.getShopId()));

        existingOrder.setClient(client);
        existingOrder.setShop(shop);
        existingOrder.setOrderNo(orderrDto.getOrderNo());
        existingOrder.setItems(orderrDto.getItems());
        existingOrder.setStatus(orderrDto.getStatus());
        existingOrder.setTotalAmount(orderrDto.getTotalAmount());

        Orderr updatedOrderr = orderrRepository.save(existingOrder);
        return mapToDto(updatedOrderr);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        if (!orderrRepository.existsById(orderId)) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
        orderrRepository.deleteById(orderId);
    }
}
