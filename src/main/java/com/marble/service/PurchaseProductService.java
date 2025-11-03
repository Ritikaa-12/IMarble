package com.marble.service;

import com.marble.dto.PurchaseProductDto;
import java.util.List;

public interface PurchaseProductService {

    PurchaseProductDto addProductToProductEntry(PurchaseProductDto dto);

    List<PurchaseProductDto> getProductsForProductEntry(Integer purchaseEntryId);

    PurchaseProductDto updateProductQuantity(Integer purchaseProductId, Integer newQuantity);

    void removeProductFromPurchaseEntry(Integer purchaseProductId);

    PurchaseProductDto getPurchaseProductById(Integer purchaseProductId);
}
