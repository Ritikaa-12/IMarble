package com.marble.dto;

import com.marble.enums.StockTransactionStatus;
import com.marble.enums.StockTransactionType;
import lombok.Data;
import java.time.LocalDate;

@Data
public class StockTransactionDto {
    private Integer stockTransId;
    private StockTransactionType type;
    private Integer quantity;
    private StockTransactionStatus status;
    private Integer referenceId;
    private LocalDate transactionDate;

    // IDs for relationships
    private Integer productId;
    private Integer shopId; // We need this to know WHICH stock to update

    // Extra details for display
    private String productName;
}