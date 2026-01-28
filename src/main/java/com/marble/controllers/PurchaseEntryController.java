package com.marble.controllers;

import com.marble.dto.PurchaseEntryDto;
import com.marble.service.PurchaseEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-entries")
public class PurchaseEntryController {

    @Autowired
    private PurchaseEntryService purchaseEntryService;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<PurchaseEntryDto> createPurchaseEntry(@RequestBody PurchaseEntryDto purchaseEntryDto) {
        PurchaseEntryDto savedEntry = purchaseEntryService.createPurchaseEntry(purchaseEntryDto);
        return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping("/")
    public ResponseEntity<List<PurchaseEntryDto>> getAllPurchaseEntries() {
        return ResponseEntity.ok(purchaseEntryService.getAllPurchaseEntry());
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseEntryDto> getPurchaseEntryById(@PathVariable Integer id) {
        PurchaseEntryDto purchaseEntry = purchaseEntryService.getPurchaseEntryById(id);
        return ResponseEntity.ok(purchaseEntry);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseEntryDto> updatePurchaseEntry(@PathVariable Integer id,
                                                                @RequestBody PurchaseEntryDto purchaseEntryDto) {
        PurchaseEntryDto updatedEntry = purchaseEntryService.updatePurchaseEntry(id, purchaseEntryDto);
        return ResponseEntity.ok(updatedEntry);
    }
    

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchaseEntry(@PathVariable Integer id) {
        purchaseEntryService.deletePurchaseEntry(id);
        return ResponseEntity.ok("Purchase Entry deleted successfully with ID: " + id);
    }
}
