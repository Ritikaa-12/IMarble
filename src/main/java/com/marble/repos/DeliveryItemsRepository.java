package com.marble.repos;

import com.marble.entities.DeliveryItems;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeliveryItemsRepository extends JpaRepository<DeliveryItems, Integer> {
    
    // Custom method to find all items for a specific delivery
    List<DeliveryItems> findByDeliveryDeliveryId(Integer deliveryId);
}