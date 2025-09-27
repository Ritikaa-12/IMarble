package com.marble.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	

	 //Many products belong to one category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // foreign key
    private Category category;
    
    
    //Many Products belong to One SubCategory
    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)  // foreign key
    private SubCategory subCategory;
	

    //Many Products belong to One Brand // ASK++++++++++++++++++++Chatg
    @ManyToMany
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    
	@Column(name="title", nullable =false)
    private String title;
	
	@Column(name="unit", nullable =false)
    private Integer unit;
	
	@Column(name="description", nullable =false)
    private String description;
	
	@Column(name="price_per_unit", nullable =false)
    private Float pricePerUnit;
	
	private String modelNo;//(HSN code)
	private String image;
	private Integer minStockLevel;
}
