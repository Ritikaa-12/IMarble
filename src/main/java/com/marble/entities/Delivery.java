package com.marble.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
// sells Dispatch
public class Delivery {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="delivery_id")
	private Integer deliveryId;
	
	//--------------------------------------
	@OneToMany
	@JoinColumn(name = "order_id",nullable = false)
	private Integer  orderId;
	//--------------------------------------
	//ask+++++ how to get a dispatcher ID
	//private Integer  dId;
	
	//ask+++++ how to get a delivery Staff ID
	
	@OneToOne                                       // ask++++++++++++++++++++++ chatg
	@JoinColumn(name = "dispatcher_id",nullable = false)
	private Staff staff;
	
	private String status;//(delivered ,on ther way,cancelled,not delivered)
	private LocalDate date;
	private String deliveredAt;//(addressssssssssssss)
}
