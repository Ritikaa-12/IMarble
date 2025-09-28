package com.marble.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity


public class Purchase_Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="purchase_product_id")
	
    private Integer purchaseProductId;
	
	//Many purchase product belongs to one product entry
	 @ManyToOne
	 @JoinColumn(name = "purchase_entry_id", nullable = false)
	 private Purchase_Entry purchaseEntry;
	 
  //Many purchase product belongs to one product id // ask ++++++++++++++Chatg
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	    
    private Integer quantity;
    private Float amount;
}
