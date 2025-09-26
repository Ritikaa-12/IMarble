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
public class Delivery {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="delivery_id")
	private Integer deliveryId;
	
	//--------------------------------------
	private Integer  orderId;
	//--------------------------------------
	//ask+++++ how to get a dispatcher ID
	//private Integer  dId;
	
	//ask+++++ how to get a delivery Staff ID
	@Column(nullable = false)
	private Integer deliverystaffId;
	
	
	
	private String status;//(delivered ,on ther way,cancelled,not delivered)
	
	private String deliveredAt;//(addressssssssssssss)
}
