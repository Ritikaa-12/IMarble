package com.marble.service;

import com.marble.dto.SellsEntryDto;
import com.marble.enums.SellsStatus;

public interface SellsEntryService {
    
   
    SellsEntryDto createSellsEntry(SellsEntryDto sellsEntryDto);

    SellsEntryDto getSellsEntryById(Integer sellsEntryId);

    SellsEntryDto updateSellsEntryStatus(Integer sellsEntryId, SellsStatus status);
}