package com.marble.service;

import com.marble.dto.DeliveryItemsDto;
import java.util.List;

public interface DeliveryItemsService {

	DeliveryItemsDto addItemToDelivery(DeliveryItemsDto itemDto);

	List<DeliveryItemsDto> getItemsForDelivery(Integer deliveryId);

	void removeItemFromDelivery(Integer deliverItemId);
	
	DeliveryItemsDto updateItemInDelivery(Integer deliverItemId, DeliveryItemsDto itemDto);
	
	List<DeliveryItemsDto> getAllDeliveryItems();

}