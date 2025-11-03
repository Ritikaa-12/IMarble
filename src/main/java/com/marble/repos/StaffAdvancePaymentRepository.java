package com.marble.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marble.entities.StaffAdvancePayment;

public interface StaffAdvancePaymentRepository extends JpaRepository<StaffAdvancePayment, Integer> {
    List<StaffAdvancePayment> findByStaff_StaffId(Integer staffId);
}
