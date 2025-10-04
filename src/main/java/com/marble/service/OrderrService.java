package com.marble.service;

import com.marble.dto.OrderrDto;
import java.util.List;

public interface OrderrService {
	OrderrDto createOrder(OrderrDto orderrDto);
	OrderrDto getOrderById(Integer orderId);
	List<OrderrDto> getAllOrders();
	OrderrDto updateOrder(Integer orderId,OrderrDto orderrDto);
	void deleteOrder(Integer orderId);
	
}
