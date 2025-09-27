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
public class Order {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer orderId;
	
	//many orders belong to one client....
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;
	  
	//many orders belong to one shop...
	@ManyToOne
	@JoinColumn(name = "shop_id", nullable = false)
	private Shop shop;
	
	@Column(nullable = false)
	private Integer orderNo;
	
	private String items;
	
	private String status;
	
	private Float totalAmount;
}
