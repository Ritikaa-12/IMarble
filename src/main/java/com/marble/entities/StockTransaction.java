package com.marble.entities;

import java.time.LocalDate;

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
public class StockTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="stock_transaction_id")
	private Integer stockTransId;
	//-------------------------------------
	@Column(nullable =false)
	private Integer productId;
	//-----------------------------------------
	@Column( nullable =false)
	private Integer branchId;
	
	@Column( nullable =false)
	private String type;
	
	
	@Column( nullable =false)
	private Integer quantity;
	
	@Column(nullable =false)
	private LocalDate date;
	
	}
