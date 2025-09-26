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
public class ReturnRequest {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="return_id")
	private Integer returnId;
	
	//--------------------------------------
	private Integer  orderId;
	//--------------------------------------
	private Integer  orderItemId;
	
	private String status;
	
	private Integer reselleableQty;

	private Integer damagedQty;
	
	
	
	
	
	
}
