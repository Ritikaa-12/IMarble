package com.marble.controllers;

import com.marble.dto.StockTransactionDto;
import com.marble.service.StockTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-transactions")
public class StockTransactionController {

    private final StockTransactionService stockTransactionService;

    public StockTransactionController(StockTransactionService stockTransactionService) {
        this.stockTransactionService = stockTransactionService;
    }

    // ---------------------- CREATE A STOCK TRANSACTION ----------------------
    @PostMapping("/create")
    public ResponseEntity<StockTransactionDto> createTransaction(@RequestBody StockTransactionDto dto) {
        StockTransactionDto created = stockTransactionService.createTransaction(dto);
        return ResponseEntity.ok(created);
    }

    // ---------------------- GET TRANSACTIONS FOR A PRODUCT ----------------------
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockTransactionDto>> getTransactionsForProduct(@PathVariable Integer productId) {
        List<StockTransactionDto> transactions = stockTransactionService.getTransactionsForProduct(productId);
        return ResponseEntity.ok(transactions);
    }
    
    @DeleteMapping("/delete/by-stock/{stockId}")
    public ResponseEntity<String> deleteTransactionsByStockId(@PathVariable Integer stockId) {
        stockTransactionService.deleteTransactionsByStockId(stockId);
        return ResponseEntity.ok("All stock transactions for stock ID " + stockId + " deleted successfully.");
    }

}
