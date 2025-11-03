package com.marble.dto;

import lombok.Data;

@Data
public class StockDto {
    private Integer stockId;
    
    // IDs for relationships
    private Integer productId;
    private Integer shopId;
    
    // Stock levels
    private Integer quantityAvailable;
    private Integer reservedQty;
    private Integer damagedQty;
    
    // Extra details for display
    private String productName;
    private String shopName;
}