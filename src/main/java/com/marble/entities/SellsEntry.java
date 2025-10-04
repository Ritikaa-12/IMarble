package com.marble.entities;

import com.marble.enums.SellsStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

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
	private SellsStatus status;    // fixed check enum sellsStatus


    // One SellsEntry ------------------> MANY SellsProduct  items.
    @OneToMany(mappedBy = "sellsEntry", cascade = CascadeType.ALL)
    private List<SellsProduct> sellsProducts;
}