package com.marble.dto;

import lombok.Data;

@Data
public class SellsProductDto {
    private Integer sellsProductId;
    private Integer quantity;
    private Float amount;


    private Integer sellsEntryId;
    private Integer productId;


    private String productName;
    private Double productPricePerUnit;
}