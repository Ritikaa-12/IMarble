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
public class SellsProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sells_prod_id")
	private Integer sellsProductId;
	
	
	@ManyToOne
	@JoinColumn(name = "sells_entry_id", nullable = false)
	private SellsEntry sellsEntry;

    @ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	private Integer quantity;
	
	private Float amount;
}
