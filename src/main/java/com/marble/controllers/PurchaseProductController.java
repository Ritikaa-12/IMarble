package com.marble.controllers;

import com.marble.dto.PurchaseProductDto;
import com.marble.service.PurchaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-products")
public class PurchaseProductController {

    @Autowired
    private PurchaseProductService purchaseProductService;

    @PostMapping("/add")
    public PurchaseProductDto addProduct(@RequestBody PurchaseProductDto dto) {
        return purchaseProductService.addProductToProductEntry(dto);
    }

    @GetMapping("/entry/{purchaseEntryId}")
    public List<PurchaseProductDto> getProductsForEntry(@PathVariable Integer purchaseEntryId) {
        return purchaseProductService.getProductsForProductEntry(purchaseEntryId);
    }

    @GetMapping("/{purchaseProductId}")
    public PurchaseProductDto getById(@PathVariable Integer purchaseProductId) {
        return purchaseProductService.getPurchaseProductById(purchaseProductId);
    }

    @PutMapping("/{purchaseProductId}/quantity/{newQuantity}")
    public PurchaseProductDto updateQuantity(@PathVariable Integer purchaseProductId,
                                             @PathVariable Integer newQuantity) {
        return purchaseProductService.updateProductQuantity(purchaseProductId, newQuantity);
    }

    @DeleteMapping("/{purchaseProductId}")
    public String removeProduct(@PathVariable Integer purchaseProductId) {
        purchaseProductService.removeProductFromPurchaseEntry(purchaseProductId);
        return "Product removed successfully.";
    }
}
