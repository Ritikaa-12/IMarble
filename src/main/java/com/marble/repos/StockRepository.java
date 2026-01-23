package com.marble.repos;

import com.marble.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    Optional<Stock> findByProductIdAndShopId(Integer productId, Integer shopId);

    List<Stock> findByShopId(Integer shopId);
}