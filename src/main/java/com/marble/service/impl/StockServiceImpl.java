package com.marble.service.impl;

import com.marble.dto.StockDto;
import com.marble.entities.Product;
import com.marble.entities.Shop;
import com.marble.entities.Stock;
import com.marble.repos.ProductRepository;
import com.marble.repos.ShopRepository;
import com.marble.repos.StockRepository;
import com.marble.service.StockService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    public StockServiceImpl(StockRepository stockRepository, ProductRepository productRepository, ShopRepository shopRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    public StockDto addStock(StockDto stockDto) {
        stockRepository.findByProductIdAndShopId(stockDto.getProductId(), stockDto.getShopId())
            .ifPresent(s -> {
                throw new RuntimeException("Stock record already exists for this product at this shop.");
            });

        Product product = productRepository.findById(stockDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Shop shop = shopRepository.findById(stockDto.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        Stock newStock = new Stock();
        newStock.setProductId(product.getProductId());
        newStock.setShopId(shop.getShopId());
        newStock.setQuantityAvailable(stockDto.getQuantityAvailable());
        newStock.setReservedQty(stockDto.getReservedQty());
        newStock.setDamagedQty(stockDto.getDamagedQty());

        Stock savedStock = stockRepository.save(newStock);
        return entityToDto(savedStock);
    }

    @Override
    public StockDto updateStock(Integer stockId, StockDto stockDto) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock record not found"));
        
        stock.setQuantityAvailable(stockDto.getQuantityAvailable());
        stock.setReservedQty(stockDto.getReservedQty());
        stock.setDamagedQty(stockDto.getDamagedQty());

        Stock updatedStock = stockRepository.save(stock);
        return entityToDto(updatedStock);
    }

    @Override
    public StockDto getStockByProductAndShop(Integer productId, Integer shopId) {
        Stock stock = stockRepository.findByProductIdAndShopId(productId, shopId)
                .orElseThrow(() -> new RuntimeException("Stock record not found"));
        return entityToDto(stock);
    }

    @Override
    public List<StockDto> getAllStockForShop(Integer shopId) {
        return stockRepository.findByShopId(shopId).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStock(Integer stockId) { // <-- ADDED
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock record not found"));
        stockRepository.delete(stock);
    }

    // Helper to convert Entity to DTO
    private StockDto entityToDto(Stock stock) {
        Product product = productRepository.findById(stock.getProductId()).orElse(null);
        Shop shop = shopRepository.findById(stock.getShopId()).orElse(null);

        StockDto dto = new StockDto();
        dto.setStockId(stock.getStockId());
        dto.setProductId(stock.getProductId());
        dto.setShopId(stock.getShopId());
        dto.setQuantityAvailable(stock.getQuantityAvailable());
        dto.setReservedQty(stock.getReservedQty());
        dto.setDamagedQty(stock.getDamagedQty());
        
        if (product != null) {
            dto.setProductName(product.getTitle());
        }
        if (shop != null) {
            dto.setShopName(shop.getName());
        }
        return dto;
    }
}