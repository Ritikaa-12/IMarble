package com.marble.controllers;

import com.marble.dto.StockDto;
import com.marble.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/add")
    public ResponseEntity<StockDto> addStock(@RequestBody StockDto stockDto) {
        StockDto createdStock = stockService.addStock(stockDto);
        return ResponseEntity.ok(createdStock);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','DISPATCHER')")
    @PutMapping("/update/{stockId}")
    public ResponseEntity<StockDto> updateStock(
            @PathVariable Integer stockId,
            @RequestBody StockDto stockDto) {

        StockDto updatedStock = stockService.updateStock(stockId, stockDto);
        return ResponseEntity.ok(updatedStock);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping("/product/{productId}/shop/{shopId}")
    public ResponseEntity<StockDto> getStockByProductAndShop(
            @PathVariable Integer productId,
            @PathVariable Integer shopId) {

        StockDto stock = stockService.getStockByProductAndShop(productId, shopId);
        return ResponseEntity.ok(stock);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<StockDto>> getAllStockForShop(@PathVariable Integer shopId) {
        List<StockDto> stockList = stockService.getAllStockForShop(shopId);
        return ResponseEntity.ok(stockList);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/delete/{stockId}")
    public ResponseEntity<String> deleteStock(@PathVariable Integer stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.ok("Stock record deleted successfully.");
    }
}
