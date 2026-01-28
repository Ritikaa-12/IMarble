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

    public StockTransactionServiceImpl(StockTransactionRepository transactionRepository,
                                       StockRepository stockRepository,
                                       ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public StockTransactionDto createTransaction(StockTransactionDto transactionDto) {

        Product product = productRepository.findById(transactionDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Stock stock = stockRepository.findByProductIdAndShopId(
                transactionDto.getProductId(),
                transactionDto.getShopId()
        ).orElseThrow(() -> new RuntimeException("Stock record not found for this product and shop"));

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
                    throw new RuntimeException("Not enough stock available. Only " +
                            stock.getQuantityAvailable() + " left.");
                }
                stock.setQuantityAvailable(stock.getQuantityAvailable() - quantity);
                break;
        }

        stockRepository.save(stock);

        StockTransaction transaction = new StockTransaction();
        transaction.setProduct(product);
        transaction.setType(transactionDto.getType());
        transaction.setQuantity(quantity);
        transaction.setStatus(StockTransactionStatus.COMPLETED);
        transaction.setReferenceId(transactionDto.getReferenceId());
        transaction.setTransactionDate(LocalDate.now());

        StockTransaction savedTransaction = transactionRepository.save(transaction);

        return entityToDto(savedTransaction);
    }

    // -------------------- UPDATE METHOD ADDED HERE --------------------

    @Override
    @Transactional
    public StockTransactionDto updateTransaction(Integer transactionId, StockTransactionDto dto) {

        // 1. Find old transaction
        StockTransaction oldTx = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        Product product = oldTx.getProduct();

        // 2. Find stock record
        Stock stock = stockRepository.findByProductIdAndShopId(
                dto.getProductId(),
                dto.getShopId()
        ).orElseThrow(() -> new RuntimeException("Stock record not found for update"));

        // ---------------- Reverse old effect ----------------
        reverseOldEffect(stock, oldTx);

        // ---------------- Update transaction fields ----------------
        oldTx.setType(dto.getType());
        oldTx.setQuantity(dto.getQuantity());
        oldTx.setReferenceId(dto.getReferenceId());
        oldTx.setTransactionDate(dto.getTransactionDate() != null ? dto.getTransactionDate() : LocalDate.now());

        // ---------------- Apply new effect ----------------
        applyNewEffect(stock, oldTx);

        stockRepository.save(stock);
        StockTransaction updatedTx = transactionRepository.save(oldTx);

        return entityToDto(updatedTx);
    }

    // -------------------- REVERSE OLD EFFECT --------------------
    private void reverseOldEffect(Stock stock, StockTransaction oldTx) {
        int qty = oldTx.getQuantity();

        switch (oldTx.getType()) {
            case PURCHASE:
            case RETURN:
                stock.setQuantityAvailable(stock.getQuantityAvailable() - qty);
                break;

            case SELLS:
            case DAMAGE:
            case MISSING:
                stock.setQuantityAvailable(stock.getQuantityAvailable() + qty);
                break;
        }
    }

    // -------------------- APPLY NEW EFFECT --------------------
    private void applyNewEffect(Stock stock, StockTransaction tx) {
        int qty = tx.getQuantity();

        switch (tx.getType()) {
            case PURCHASE:
            case RETURN:
                stock.setQuantityAvailable(stock.getQuantityAvailable() + qty);
                break;

            case SELLS:
            case DAMAGE:
            case MISSING:
                if (stock.getQuantityAvailable() < qty) {
                    throw new RuntimeException("Not enough stock available after update!");
                }
                stock.setQuantityAvailable(stock.getQuantityAvailable() - qty);
                break;
        }
    }

    @Override
    public List<StockTransactionDto> getTransactionsForProduct(Integer productId) {
        List<StockTransaction> transactions =
                transactionRepository.findByProductProductId(productId);

        return transactions.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteTransactionsByStockId(Integer stockId) {
        List<StockTransaction> transactions =
                transactionRepository.findByStockStockId(stockId);

        if (transactions.isEmpty()) {
            throw new RuntimeException("No transactions found for stock ID: " + stockId);
        }

        transactionRepository.deleteAll(transactions);
    }

    private StockTransactionDto entityToDto(StockTransaction tx) {
        StockTransactionDto dto = new StockTransactionDto();
        dto.setStockTransId(tx.getStockTransId());
        dto.setType(tx.getType());
        dto.setQuantity(tx.getQuantity());
        dto.setStatus(tx.getStatus());
        dto.setReferenceId(tx.getReferenceId());
        dto.setTransactionDate(tx.getTransactionDate());
        dto.setProductId(tx.getProduct().getProductId());
        dto.setProductName(tx.getProduct().getTitle());
        return dto;
    }
}
