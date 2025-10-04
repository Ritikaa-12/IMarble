package com.marble.service;

import com.marble.dto.OrderItemDto1;
import java.util.List;

public interface OrderitemService {
	OrderItemDto1 createOrderItem(OrderItemDto1 orderItemDto);
	OrderItemDto1 getOrderItemById(Integer orderItemId);
	List<OrderItemDto1> getAllOrderItems();
	OrderItemDto1 updateOrderItem(Integer orderItemId,OrderItemDto1 orderItemDto1);
	void deleteOrderItem(Integer orderItemId);
}

