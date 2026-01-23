package com.marble.service;

import com.marble.dto.StockDto;
import java.util.List;

public interface StockService {

    StockDto addStock(StockDto stockDto);

    StockDto updateStock(Integer stockId, StockDto stockDto);

    StockDto getStockByProductAndShop(Integer productId, Integer shopId);

    List<StockDto> getAllStockForShop(Integer shopId);

    /**
     * Deletes a stock record by its ID.
     */
    void deleteStock(Integer stockId); // <-- ADDED
}