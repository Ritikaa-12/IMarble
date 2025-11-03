package com.marble.repos;

import com.marble.entities.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Integer> {

    // Finds all transactions for a specific product
    List<StockTransaction> findByProductProductId(Integer productId);
}