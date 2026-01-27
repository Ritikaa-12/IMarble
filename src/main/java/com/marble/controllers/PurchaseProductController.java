package com.marble.controllers;

import com.marble.dto.PurchaseProductDto;
import com.marble.service.PurchaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-products")
public class PurchaseProductController {

    @Autowired
    private PurchaseProductService purchaseProductService;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/add")
    public PurchaseProductDto addProduct(@RequestBody PurchaseProductDto dto) {
        return purchaseProductService.addProductToProductEntry(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping("/entry/{purchaseEntryId}")
    public List<PurchaseProductDto> getProductsForEntry(@PathVariable Integer purchaseEntryId) {
        return purchaseProductService.getProductsForProductEntry(purchaseEntryId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping("/{purchaseProductId}")
    public PurchaseProductDto getById(@PathVariable Integer purchaseProductId) {
        return purchaseProductService.getPurchaseProductById(purchaseProductId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/{purchaseProductId}/quantity/{newQuantity}")
    public PurchaseProductDto updateQuantity(@PathVariable Integer purchaseProductId,
                                             @PathVariable Integer newQuantity) {
        return purchaseProductService.updateProductQuantity(purchaseProductId, newQuantity);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/{purchaseProductId}")
    public String removeProduct(@PathVariable Integer purchaseProductId) {
        purchaseProductService.removeProductFromPurchaseEntry(purchaseProductId);
        return "Product removed successfully.";
    }
}
