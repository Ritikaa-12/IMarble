// src/main/java/com/marble/dto/ProductDto.java
package com.marble.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Integer productId;
    private String title;
    private String description;
    private Double pricePerUnit;
    private String unit;
    private String modelNo;
    private String image;
    private Integer minStockLevel;
    private Integer categoryId; 
    // We'll use these IDs to create the product
    private Integer subCategoryId;
    private Integer brandId;

    // We can also add these fields to display product info easily
    private String subCategoryTitle;
    private String brandTitle;
}