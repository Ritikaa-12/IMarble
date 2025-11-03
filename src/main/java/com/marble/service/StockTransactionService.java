package com.marble.service;

import com.marble.dto.StockTransactionDto;
import java.util.List;

public interface StockTransactionService {

    /**
     * Creates a new stock transaction AND updates the main stock level.
     * This is the most important method.
     */
    StockTransactionDto createTransaction(StockTransactionDto transactionDto);

    /**
     * Gets all transactions for a specific product.
     */
    List<StockTransactionDto> getTransactionsForProduct(Integer productId);
}