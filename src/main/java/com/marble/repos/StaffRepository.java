package com.marble.repos;

import com.marble.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    
   
    Optional<Staff> findByUserUserId(Integer userId);
}
