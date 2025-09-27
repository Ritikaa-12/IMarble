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
public class Sells_product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sells_prod_id")
	private Integer sellsProductId;
	
	//bht sare sells product ki single entry ho sakti hai
	@ManyToOne
	@JoinColumn(name = "sells_entry_id", nullable = false)
	private Sells_Entry sellsEntry;

	//bht sare sells product ek single product id ko belong kr sakte hai
    @ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	private Integer quantity;
	
	private Float amount;
}
