package com.marble.service;

import com.marble.dto.SellsEntryDto;
import com.marble.enums.SellsStatus;
import java.util.List;

public interface SellsEntryService {
    
    SellsEntryDto createSellsEntry(SellsEntryDto sellsEntryDto);

    SellsEntryDto getSellsEntryById(Integer sellsEntryId);

    SellsEntryDto updateSellsEntryStatus(Integer sellsEntryId, SellsStatus status);
    
    List<SellsEntryDto> getAllSellsEntries();
    
    List<SellsEntryDto> getSellsEntriesByClient(Integer clientId);
    
    SellsEntryDto getSellsEntryByInvoiceNo(String invoiceNo);
    
    void removeSellsEntry(Integer sellsEntryId);
}