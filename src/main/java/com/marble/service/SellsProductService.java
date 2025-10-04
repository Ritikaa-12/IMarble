package com.marble.service;

import com.marble.dto.SellsProductDto;
import java.util.List;

public interface SellsProductService {

  
    SellsProductDto addProductToSellsEntry(SellsProductDto sellsProductDto);

  
    List<SellsProductDto> getProductsForSellsEntry(Integer sellsEntryId);

  
    SellsProductDto updateProductQuantity(Integer sellsProductId, Integer newQuantity);

  
    void removeProductFromSellsEntry(Integer sellsProductId);
}