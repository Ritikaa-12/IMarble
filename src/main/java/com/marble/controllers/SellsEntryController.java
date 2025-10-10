package com.marble.controllers;

import com.marble.dto.SellsEntryDto;
import com.marble.enums.SellsStatus;
import com.marble.service.SellsEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sells")
@CrossOrigin(origins = "*")
public class SellsEntryController {

    @Autowired
    private SellsEntryService sellsEntryService;

    @PostMapping("/create")
    public ResponseEntity<SellsEntryDto> createSellsEntry(@RequestBody SellsEntryDto sellsEntryDto) {
        SellsEntryDto createdEntry = sellsEntryService.createSellsEntry(sellsEntryDto);
        return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SellsEntryDto>> getAllSellsEntries() {
        List<SellsEntryDto> allEntries = sellsEntryService.getAllSellsEntries();
        return ResponseEntity.ok(allEntries);
    }

    @GetMapping("/{sellsEntryId}")
    public ResponseEntity<SellsEntryDto> getSellsEntryById(@PathVariable Integer sellsEntryId) {
        SellsEntryDto entry = sellsEntryService.getSellsEntryById(sellsEntryId);
        return ResponseEntity.ok(entry);
    }

    @GetMapping("/invoice/{invoiceNo}")
    public ResponseEntity<SellsEntryDto> getSellsEntryByInvoiceNo(@PathVariable String invoiceNo) {
        SellsEntryDto entry = sellsEntryService.getSellsEntryByInvoiceNo(invoiceNo);
        return ResponseEntity.ok(entry);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<SellsEntryDto>> getSellsEntriesByClient(@PathVariable Integer clientId) {
        List<SellsEntryDto> entries = sellsEntryService.getSellsEntriesByClient(clientId);
        return ResponseEntity.ok(entries);
    }
    
    @PutMapping("/{sellsEntryId}/status")
    public ResponseEntity<SellsEntryDto> updateSellsEntryStatus(
            @PathVariable Integer sellsEntryId,
            @RequestParam SellsStatus status) {

        SellsEntryDto updatedEntry = sellsEntryService.updateSellsEntryStatus(sellsEntryId, status);
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/{sellsEntryId}")
    public ResponseEntity<String> deleteSellsEntry(@PathVariable Integer sellsEntryId) {
        sellsEntryService.removeSellsEntry(sellsEntryId);
        return ResponseEntity.ok("Sells Entry deleted successfully with ID: " + sellsEntryId);
    }
}