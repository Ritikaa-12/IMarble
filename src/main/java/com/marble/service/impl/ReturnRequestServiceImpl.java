package com.marble.service.impl;

import com.marble.dto.ReturnRequestDto;
import com.marble.entities.Orderr;
import com.marble.entities.OrderItem;
import com.marble.entities.ReturnRequest;
import com.marble.enums.ReturnRequestStatus;
import com.marble.repos.OrderItemRepository;
import com.marble.repos.OrderrRepository;
import com.marble.repos.ReturnRequestRepository;
import com.marble.service.ReturnRequestService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReturnRequestServiceImpl implements ReturnRequestService {

    private final ReturnRequestRepository returnRequestRepository;
    private final OrderrRepository orderrRepository;
    private final OrderItemRepository orderItemRepository;

    public ReturnRequestServiceImpl(ReturnRequestRepository returnRequestRepository, OrderrRepository orderrRepository, OrderItemRepository orderItemRepository) {
        this.returnRequestRepository = returnRequestRepository;
        this.orderrRepository = orderrRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public ReturnRequestDto createReturnRequest(ReturnRequestDto returnRequestDto) {
        if (returnRequestDto.getOrderId() == null) {
            throw new IllegalArgumentException("Order ID must not be null");
        }
        if (returnRequestDto.getOrderItemId() == null) {
            throw new IllegalArgumentException("Order Item ID must not be null");
        }

        Orderr order = orderrRepository.findById(returnRequestDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderItem orderItem = orderItemRepository.findById(returnRequestDto.getOrderItemId())
                .orElseThrow(() -> new RuntimeException("Order item not found"));

        if (returnRequestDto.getQuantity() > orderItem.getQuantity()) {
            throw new RuntimeException("Cannot return more items than were purchased in that line item.");
        }

        ReturnRequest request = new ReturnRequest();
        request.setOrder(order);
        request.setOrderItem(orderItem);
        request.setQuantity(returnRequestDto.getQuantity());
        request.setType(returnRequestDto.getType());
        request.setDescription(returnRequestDto.getDescription());
        request.setStatus(ReturnRequestStatus.PENDING_APPROVAL);

        ReturnRequest savedRequest = returnRequestRepository.save(request);
        return entityToDto(savedRequest);
    }


    @Override
    public ReturnRequestDto approveReturnRequest(Integer returnId) {
        ReturnRequest request = returnRequestRepository.findById(returnId)
                .orElseThrow(() -> new RuntimeException("Return request not found"));
        
        request.setStatus(ReturnRequestStatus.APPROVED);
        ReturnRequest updatedRequest = returnRequestRepository.save(request);
        
        // TODO: Add logic here to update stock and trigger a refund.
        
        return entityToDto(updatedRequest);
    }

    @Override
    public ReturnRequestDto rejectReturnRequest(Integer returnId) {
        ReturnRequest request = returnRequestRepository.findById(returnId)
                .orElseThrow(() -> new RuntimeException("Return request not found"));
        
        request.setStatus(ReturnRequestStatus.REJECTED);
        ReturnRequest updatedRequest = returnRequestRepository.save(request);
        
        return entityToDto(updatedRequest);
    }

    @Override
    public List<ReturnRequestDto> getRequestsForOrder(Integer orderId) {
        return returnRequestRepository.findByOrderOrderId(orderId).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReturnRequestDto> getRequestsByStatus(ReturnRequestStatus status) {
        return returnRequestRepository.findByStatus(status).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Helper to convert Entity to DTO
    private ReturnRequestDto entityToDto(ReturnRequest request) {
        ReturnRequestDto dto = new ReturnRequestDto();
        dto.setReturnId(request.getReturnId());
        dto.setQuantity(request.getQuantity());
        dto.setType(request.getType());
        dto.setDescription(request.getDescription());
        dto.setStatus(request.getStatus());
        dto.setOrderId(request.getOrder().getOrderId());
        dto.setOrderItemId(request.getOrderItem().getOrderItemId());
        dto.setProductName(request.getOrderItem().getProduct().getTitle()); // Get product name
        return dto;
    }
}