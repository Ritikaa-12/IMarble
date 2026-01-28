package com.marble.controllers;

import com.marble.dto.SellsProductDto;
import com.marble.service.SellsProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sells-products")
public class SellsProductController {

    private final SellsProductService sellsProductService;

    public SellsProductController(SellsProductService sellsProductService) {
        this.sellsProductService = sellsProductService;
    }

    // RECEPTIONIST + ADMIN can add product to a sales entry
    @PreAuthorize("hasAnyRole('RECEPTIONIST','ADMIN')")
    @PostMapping("/")
    public ResponseEntity<SellsProductDto> addProductToSellsEntry(@RequestBody SellsProductDto sellsProductDto) {
        SellsProductDto newSellsProduct = sellsProductService.addProductToSellsEntry(sellsProductDto);
        return new ResponseEntity<>(newSellsProduct, HttpStatus.CREATED);
    }

    // MANAGER + ADMIN can view products for a sale
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @GetMapping("/by-sells-entry/{sellsEntryId}")
    public ResponseEntity<List<SellsProductDto>> getProductsForSellsEntry(@PathVariable Integer sellsEntryId) {
        List<SellsProductDto> products = sellsProductService.getProductsForSellsEntry(sellsEntryId);
        return ResponseEntity.ok(products);
    }

    // Only ADMIN can update quantity
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{sellsProductId}/quantity")
    public ResponseEntity<SellsProductDto> updateProductQuantity(
            @PathVariable Integer sellsProductId,
            @RequestParam Integer newQuantity) {

        SellsProductDto updatedProduct = sellsProductService.updateProductQuantity(sellsProductId, newQuantity);
        return ResponseEntity.ok(updatedProduct);
    }

    // Only ADMIN can delete a product from a sale
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{sellsProductId}")
    public ResponseEntity<String> removeProductFromSellsEntry(@PathVariable Integer sellsProductId) {
        sellsProductService.removeProductFromSellsEntry(sellsProductId);
        return ResponseEntity.ok("Sells product with ID: " + sellsProductId + " was removed successfully.");
    }
}
