package com.marble.controllers;

import com.marble.dto.StockTransactionDto;
import com.marble.service.StockTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-transactions")
public class StockTransactionController {

    private final StockTransactionService stockTransactionService;

    public StockTransactionController(StockTransactionService stockTransactionService) {
        this.stockTransactionService = stockTransactionService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<StockTransactionDto> createTransaction(@RequestBody StockTransactionDto dto) {
        StockTransactionDto created = stockTransactionService.createTransaction(dto);
        return ResponseEntity.ok(created);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockTransactionDto>> getTransactionsForProduct(@PathVariable Integer productId) {
        List<StockTransactionDto> transactions = stockTransactionService.getTransactionsForProduct(productId);
        return ResponseEntity.ok(transactions);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<String> deleteTransactionsByStockId(@PathVariable Integer stockId) {
        stockTransactionService.deleteTransactionsByStockId(stockId);
        return ResponseEntity.ok("All stock transactions for stock ID " + stockId + " deleted successfully.");
    }

}
