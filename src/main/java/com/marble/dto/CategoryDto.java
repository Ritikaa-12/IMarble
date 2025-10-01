package com.marble.dto;

import lombok.Data;

@Data
public class CategoryDto {

	private Integer categoryId;
	private String title;
	private String description;
	private String images ;
	private Boolean status;
}
