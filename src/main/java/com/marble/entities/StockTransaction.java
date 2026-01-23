package com.marble.entities;

import com.marble.enums.StockTransactionStatus;
import com.marble.enums.StockTransactionType;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class StockTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_trans_id")
	private Integer stockTransId;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	
    @Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StockTransactionType type;
	
	@Column(nullable = false)
	private Integer quantity;
	
    @Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StockTransactionStatus status;
	
    // ID of the SellsEntry, PurchaseEntry, or ReturnRequest
	private Integer referenceId; 

    private LocalDate transactionDate;
    
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

}