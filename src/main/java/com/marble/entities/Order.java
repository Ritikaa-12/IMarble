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
public class Order {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer orderId;
	
	//--------------------------------------
	private Integer  customerId;
	//--------------------------------------
	private Integer  branchId;
	
	@Column(nullable = false)
	private Integer orderNo;
	
	private String items;
	
	private String status;
	
	private Float totalAmount;
}
