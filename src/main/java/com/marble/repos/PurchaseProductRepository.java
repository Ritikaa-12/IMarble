package com.marble.repos;

import com.marble.entities.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Integer> {
    List<PurchaseProduct> findByPurchaseEntry_PurchaseEntryId(Integer purchaseEntryId);
}
