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
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="stock_id")
	private Integer stockId;
	
	@Column(nullable =false)
	private Integer productId;
	
	@Column(nullable =false)
	private Integer shopId;
	
	@Column( nullable =false)
	private Integer quantityAvailable;
	
	@Column( nullable =false)
	private Integer reservedQty;
	
	@Column( nullable =false)
	private Integer damagedQty;
	

//	@Column( nullable =false)
//	private Integer stolenQty;
	
}
