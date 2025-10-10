package com.marble.entities;

import com.marble.enums.SellsStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SellsEntry {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sellsEntryId;
	
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
	private String type; // e.g., "client", "dealer"
	private Float billAmount;
	private LocalDate date;
	
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
	
    private String invoiceNo;
    
    @Enumerated(EnumType.STRING)
	private SellsStatus status;

    // ERROR WAS HERE: This list was missing.
    // This connects the SellsEntry to all of its line items.
    @OneToMany(mappedBy = "sellsEntry", cascade = CascadeType.ALL)
    private List<SellsProduct> sellsProducts;
}