package com.marble.controllers;

import com.marble.dto.PurchaseEntryDto;
import com.marble.service.PurchaseEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-entries")
public class PurchaseEntryController {

    @Autowired
    private PurchaseEntryService purchaseEntryService;

    @PostMapping("/create")
    public ResponseEntity<PurchaseEntryDto> createPurchaseEntry(@RequestBody PurchaseEntryDto purchaseEntryDto) {
        PurchaseEntryDto savedEntry = purchaseEntryService.createPurchaseEntry(purchaseEntryDto);
        return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PurchaseEntryDto>> getAllPurchaseEntries() {
        return ResponseEntity.ok(purchaseEntryService.getAllPurchaseEntry());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseEntryDto> getPurchaseEntryById(@PathVariable Integer id) {
        PurchaseEntryDto purchaseEntry = purchaseEntryService.getPurchaseEntryById(id);
        return ResponseEntity.ok(purchaseEntry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseEntryDto> updatePurchaseEntry(@PathVariable Integer id,
                                                                @RequestBody PurchaseEntryDto purchaseEntryDto) {
        PurchaseEntryDto updatedEntry = purchaseEntryService.updatePurchaseEntry(id, purchaseEntryDto);
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchaseEntry(@PathVariable Integer id) {
        purchaseEntryService.deletePurchaseEntry(id);
        return ResponseEntity.ok("Purchase Entry deleted successfully with ID: " + id);
    }
}
