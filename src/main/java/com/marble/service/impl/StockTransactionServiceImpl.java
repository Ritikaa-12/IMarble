package com.marble.service.impl;

import com.marble.dto.StockTransactionDto;
import com.marble.entities.Product;
import com.marble.entities.Stock;
import com.marble.entities.StockTransaction;
import com.marble.enums.StockTransactionStatus;
import com.marble.enums.StockTransactionType;
import com.marble.repos.ProductRepository;
import com.marble.repos.StockRepository;
import com.marble.repos.StockTransactionRepository;
import com.marble.service.StockTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockTransactionServiceImpl implements StockTransactionService {

    private final StockTransactionRepository transactionRepository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    public StockTransactionServiceImpl(StockTransactionRepository transactionRepository, StockRepository stockRepository, ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional // This ensures both tables (Stock and StockTransaction) update together
    public StockTransactionDto createTransaction(StockTransactionDto transactionDto) {
        
        // 1. Find the product
        Product product = productRepository.findById(transactionDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 2. Find the main stock record for this product and shop
        Stock stock = stockRepository.findByProductIdAndShopId(transactionDto.getProductId(), transactionDto.getShopId())
                .orElseThrow(() -> new RuntimeException("Stock record not found for this product and shop"));

        // 3. Update the stock quantity based on the transaction type
        int quantity = transactionDto.getQuantity();
        switch (transactionDto.getType()) {
            case PURCHASE:
            case RETURN:
                stock.setQuantityAvailable(stock.getQuantityAvailable() + quantity);
                break;
            case SELLS:
            case DAMAGE:
            case MISSING:
                if (stock.getQuantityAvailable() < quantity) {
                    throw new RuntimeException("Not enough stock available. Only " + stock.getQuantityAvailable() + " left.");
                }
                stock.setQuantityAvailable(stock.getQuantityAvailable() - quantity);
                break;
        }

        // 4. Save the updated stock record
        stockRepository.save(stock);

        // 5. Create and save the new transaction log
        StockTransaction transaction = new StockTransaction();
        transaction.setProduct(product);
        transaction.setType(transactionDto.getType());
        transaction.setQuantity(quantity);
        transaction.setStatus(StockTransactionStatus.COMPLETED); // Assuming it's immediate
        transaction.setReferenceId(transactionDto.getReferenceId());
        transaction.setTransactionDate(LocalDate.now());

        StockTransaction savedTransaction = transactionRepository.save(transaction);
        
        return entityToDto(savedTransaction);
    }

    @Override
    public List<StockTransactionDto> getTransactionsForProduct(Integer productId) {
        List<StockTransaction> transactions = transactionRepository.findByProductProductId(productId);
        return transactions.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    // Helper to convert Entity to DTO
    private StockTransactionDto entityToDto(StockTransaction transaction) {
        StockTransactionDto dto = new StockTransactionDto();
        dto.setStockTransId(transaction.getStockTransId());
        dto.setType(transaction.getType());
        dto.setQuantity(transaction.getQuantity());
        dto.setStatus(transaction.getStatus());
        dto.setReferenceId(transaction.getReferenceId());
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setProductId(transaction.getProduct().getProductId());
        dto.setProductName(transaction.getProduct().getTitle());
        // We don't store shopId in the transaction, so we can't set it here
        return dto;
    }
}