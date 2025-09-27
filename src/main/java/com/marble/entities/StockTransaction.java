package com.marble.entities;

import java.time.LocalDate;

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
public class StockTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="stock_transaction_id")
	private Integer stockTransId;
	
	//-------------------------------
	@ManyToOne
	@JoinColumn(name="product_id",nullable=false)
	private Product product;
	
	//-----------------------------------------
	@ManyToOne
	@JoinColumn(name="shop_id",nullable=false)
	private Shop shop;
	
	@Column( nullable =false)
	private String type;
	
	@Column( nullable =false)
	private Integer quantity;
	
	@Column(nullable =false)
	private LocalDate date;
	
	}
