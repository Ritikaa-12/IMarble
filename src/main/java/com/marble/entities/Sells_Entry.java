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
public class Sells_Entry {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sells_entry_id")
	private Integer sellsEntryId;
	
	//we should not keep sellsProductId inside 
	//SellsEntry because products are linked via SellsProduct entity.
	private Integer sellsProductId;
	
	//many sells entry belong to one dealer
    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = false)
    private Dealer dealer;
    
	//-----------------------------------------------------------
	private String type;
	private Float billAmount;
	private LocalDate date;
	
    //bht sari sells entry ek shop ko belong kr skti hai
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
	
	//(booked/dispatched/done)
	private String status;
	
}
