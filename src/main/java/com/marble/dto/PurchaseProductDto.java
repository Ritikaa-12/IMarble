package com.marble.dto;

import lombok.Data;

@Data
public class PurchaseProductDto {
    private Integer purchaseProductId;

    private Integer purchaseEntryId; 
    private Integer productId;        
    private Integer quantity;
    private Float amount;
}
