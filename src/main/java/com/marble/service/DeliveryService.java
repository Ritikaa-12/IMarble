package com.marble.service;

import com.marble.dto.DeliveryDto;
import java.util.List;

public interface DeliveryService {

    DeliveryDto createDelivery(DeliveryDto deliveryDto);

    DeliveryDto assignStaff(Integer deliveryId, Integer staffId);

    DeliveryDto updateStatus(Integer deliveryId, String status);

    List<DeliveryDto> getDeliveriesByStaff(Integer staffId);

    DeliveryDto getDeliveryById(Integer deliveryId);

    void deleteDeliveryId(Integer deliveryId);
}
