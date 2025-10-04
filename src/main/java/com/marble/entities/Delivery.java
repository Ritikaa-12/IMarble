package com.marble.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Delivery {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deliveryId;
	
    
	@ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orderr order;


	@ManyToOne                                       
	@JoinColumn(name = "dispatcher_id") 
	private Staff staff;
	

    
	private String status;

	private LocalDate date;

    @Column(name="delivery_address") 
	private String deliveryAddress;
}