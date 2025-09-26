package com.marble.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
    private Integer productId;
	
	//---------------------
	@Column(name="category_id", nullable =false)
    private Integer categoryId;
	//---------------------	
	@Column(name="subcategory_id", nullable =false)
    private Integer subCategoryId;
	
	@Column(name="title", nullable =false)
    private String title;
	
	@Column(name="unit", nullable =false)
    private Integer unit;
	
	@Column(name="description", nullable =false)
    private String description;
	
	@Column(name="price_per_unit", nullable =false)
    private Float pricePerUnit;
	
	//---------------------
	@Column(name="brand_id", nullable =false)
    private String brandId;
}
