package com.marble.entities;

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
// Dispatch Item
public class DeliveryItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deliverItemId;
	
	@ManyToOne
	@JoinColumn(name="delivery_id",nullable=false)
	private Delivery delivery;
	
    @ManyToOne
    @JoinColumn(name = "sells_prod_id", nullable = false)
    private SellsProduct sells_product;
    
    private Integer quantity;
	
	
}
