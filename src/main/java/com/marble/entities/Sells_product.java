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
public class Sells_product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dealer_id")
	private Integer sellsProductId;
	
	private Integer entryId;
	private Integer productId;
	private Integer quantity;
	private Float amount;
}
