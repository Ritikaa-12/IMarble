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
public class Purchase_Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="purchase_entry_id")
	private Integer purchaseEntryId;
	
	@Column(name="dealer_id", nullable =false)
	private Integer dealerId;
	
	@Column(name="total_amount", nullable =false)
	private Float totalAmount;
	
	private LocalDate purchaseDate;
	private Integer invoiceNo;
	
}
