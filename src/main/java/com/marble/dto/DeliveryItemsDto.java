package com.marble.dto;

import lombok.Data;

@Data
public class DeliveryItemsDto {
    private Integer deliverItemId;
    private Integer quantity;


    private Integer deliveryId;
    private Integer sellsProductId;
    private String productName; 
}