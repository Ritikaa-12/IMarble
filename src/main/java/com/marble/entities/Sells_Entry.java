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
public class Sells_Entry {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sells_entry_id")
	private Integer sellsEntryId;
	
	//-----------------------------------------------------------
	private Integer sellsProductId;
	//-----------------------------------------------------------
	private Integer dealerId;
	//-----------------------------------------------------------
	private String type;
	private Float billAmount;
	private LocalDate date;
	//-----------------------------------------------------------
	private Integer shopId;
	//(booked/dispatched/done)
	private String status;
	
}
