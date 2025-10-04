package com.marble.repos;

import com.marble.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    
    
    List<Delivery> findByStaffStaffId(Integer staffId);
}