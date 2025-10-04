package com.marble.service;

import com.marble.dto.PurchaseEntryDto;
import java.util.List;

public interface PurchaseEntryService {
	
    PurchaseEntryDto createPurchaseEntry(PurchaseEntryDto PurchaseEntryDto);
    PurchaseEntryDto getPurchaseEntryById(Integer PurchaseEntryId);
    List<PurchaseEntryDto> getAllPurchaseEntry();
    PurchaseEntryDto updatePurchaseEntry(Integer PurchaseEntryId, PurchaseEntryDto PurchaseEntryDto);
    void deletePurchaseEntry(Integer PurchaseEntryId);
}
