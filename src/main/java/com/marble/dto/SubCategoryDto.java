package com.marble.dto;

import lombok.Data;

@Data
public class SubCategoryDto {
    private Integer subCategoryId;
    private String title;
    private String description;
    private String images;
    private Boolean status; 
    private Integer categoryId;
}