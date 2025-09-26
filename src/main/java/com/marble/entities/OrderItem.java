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
public class OrderItem {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderItem_id")
	private Integer orderItemId;
	
	//--------------------------------------
	private Integer  orderId;
	//--------------------------------------
	private Integer  productId;
	
	@Column(nullable = false)
	private Integer quantity;
	
	private Float discount;
	// ask+++++++++++++++++++++++++++++++++++
	private Float tax;
	
	private Float price;
	
	private Float subTotal;
}
