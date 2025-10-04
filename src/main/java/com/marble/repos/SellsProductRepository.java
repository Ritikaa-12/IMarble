package com.marble.repos;

import com.marble.entities.SellsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SellsProductRepository extends JpaRepository<SellsProduct, Integer> {
    
   
    List<SellsProduct> findBySellsEntrySellsEntryId(Integer sellsEntryId);
}