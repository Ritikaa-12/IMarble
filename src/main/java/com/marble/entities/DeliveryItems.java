package com.marble.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DeliveryItems {

	private Integer deliverItemId;
	
	@ManyToOne
	@JoinColumn(name="delivery_id",nullable=false)
	private Delivery delivery;
	
    @ManyToOne
    @JoinColumn(name = "sells_prod_id", nullable = false)
    private Sells_product sells_product;
    
    private Integer quantity;
	
	
}
