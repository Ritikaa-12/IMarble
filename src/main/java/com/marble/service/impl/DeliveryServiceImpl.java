package com.marble.service.impl;

import com.marble.dto.DeliveryDto;
import com.marble.entities.Client;
import com.marble.entities.Delivery;
import com.marble.entities.Orderr;
import com.marble.entities.Staff;
import com.marble.repos.DeliveryRepository;
import com.marble.repos.OrderrRepository;
import com.marble.repos.StaffRepository;
import com.marble.service.DeliveryService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderrRepository orderrRepository;
    private final StaffRepository staffRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, OrderrRepository orderrRepository, StaffRepository staffRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderrRepository = orderrRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {
        // Find the order that this delivery belongs to
        Orderr order = orderrRepository.findById(deliveryDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        delivery.setDeliveryAddress(deliveryDto.getDeliveryAddress());
        delivery.setDate(LocalDate.now());
        delivery.setStatus("PENDING"); // Set the initial status
        
        Delivery savedDelivery = deliveryRepository.save(delivery);
        return entityToDto(savedDelivery);
    }

    @Override
    public DeliveryDto assignStaff(Integer deliveryId, Integer staffId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff (Dispatcher) not found"));

        delivery.setStaff(staff);
        Delivery updatedDelivery = deliveryRepository.save(delivery);
        return entityToDto(updatedDelivery);
    }

    @Override
    public DeliveryDto updateStatus(Integer deliveryId, String status) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        
        delivery.setStatus(status);
        Delivery updatedDelivery = deliveryRepository.save(delivery);
        return entityToDto(updatedDelivery);
    }

    @Override
    public List<DeliveryDto> getDeliveriesByStaff(Integer staffId) {
        List<Delivery> deliveries = deliveryRepository.findByStaffStaffId(staffId);
        return deliveries.stream().map(this::entityToDto).collect(Collectors.toList());
    }



    @Override
    public DeliveryDto getDeliveryById(Integer deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        return entityToDto(delivery);
    }
    

    private DeliveryDto entityToDto(Delivery delivery) {
        DeliveryDto dto = new DeliveryDto();
        dto.setDeliveryId(delivery.getDeliveryId());
        dto.setDate(delivery.getDate());
        dto.setStatus(delivery.getStatus());
        dto.setDeliveryAddress(delivery.getDeliveryAddress());
        dto.setOrderId(delivery.getOrder().getOrderId());
    
        if (delivery.getStaff() != null) {
            dto.setStaffId(delivery.getStaff().getStaffId());
        }
        return dto;
    }

	@Override
	public void deleteDeliveryId(Integer deliveryId) {
		Delivery delivery=deliveryRepository.findById(deliveryId).orElseThrow(() -> new RuntimeException("Delivery not found with ID"+ deliveryId));
    	deliveryRepository.delete(delivery);
		
	}
}