package com.marble.dto;

import lombok.Data;

@Data
public class OrderItemDto1 {

private Integer orderItemId;
	
    private Integer orderId;
    private Integer productId;
	private Integer quantity;
	private Float discount;
	private Float tax;
	private Float price;
	private Float subTotal;
}

