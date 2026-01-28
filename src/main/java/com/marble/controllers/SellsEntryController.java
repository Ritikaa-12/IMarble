package com.marble.controllers;

import com.marble.dto.SellsEntryDto;
import com.marble.enums.SellsStatus;
import com.marble.service.SellsEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sells")
@CrossOrigin(origins = "*")
public class SellsEntryController {

    @Autowired
    private SellsEntryService sellsEntryService;

    // RECEPTIONIST + ADMIN can create sales
    @PreAuthorize("hasAnyRole('RECEPTIONIST','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<SellsEntryDto> createSellsEntry(@RequestBody SellsEntryDto sellsEntryDto) {
        SellsEntryDto createdEntry = sellsEntryService.createSellsEntry(sellsEntryDto);
        return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
    }

    // MANAGER + ADMIN can view all records
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<SellsEntryDto>> getAllSellsEntries() {
        List<SellsEntryDto> allEntries = sellsEntryService.getAllSellsEntries();
        return ResponseEntity.ok(allEntries);
    }

    // MANAGER + ADMIN can view details by ID
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @GetMapping("/{sellsEntryId}")
    public ResponseEntity<SellsEntryDto> getSellsEntryById(@PathVariable Integer sellsEntryId) {
        SellsEntryDto entry = sellsEntryService.getSellsEntryById(sellsEntryId);
        return ResponseEntity.ok(entry);
    }

    // MANAGER + ADMIN can view invoice details
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @GetMapping("/invoice/{invoiceNo}")
    public ResponseEntity<SellsEntryDto> getSellsEntryByInvoiceNo(@PathVariable String invoiceNo) {
        SellsEntryDto entry = sellsEntryService.getSellsEntryByInvoiceNo(invoiceNo);
        return ResponseEntity.ok(entry);
    }

    // CLIENT can view their own purchases
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<SellsEntryDto>> getSellsEntriesByClient(@PathVariable Integer clientId) {
        List<SellsEntryDto> entries = sellsEntryService.getSellsEntriesByClient(clientId);
        return ResponseEntity.ok(entries);
    }

    // Only ADMIN can update status
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{sellsEntryId}/status")
    public ResponseEntity<SellsEntryDto> updateSellsEntryStatus(
            @PathVariable Integer sellsEntryId,
            @RequestParam SellsStatus status) {

        SellsEntryDto updatedEntry = sellsEntryService.updateSellsEntryStatus(sellsEntryId, status);
        return ResponseEntity.ok(updatedEntry);
    }

    // Only ADMIN can delete sales entry
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{sellsEntryId}")
    public ResponseEntity<String> deleteSellsEntry(@PathVariable Integer sellsEntryId) {
        sellsEntryService.removeSellsEntry(sellsEntryId);
        return ResponseEntity.ok("Sells Entry deleted successfully with ID: " + sellsEntryId);
    }
}
