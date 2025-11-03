package com.marble.repos;

import com.marble.entities.ReturnRequest;
import com.marble.enums.ReturnRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Integer> {

    // Finds all return requests for a specific order
    List<ReturnRequest> findByOrderOrderId(Integer orderId);

    // Finds all return requests that have a specific status
    List<ReturnRequest> findByStatus(ReturnRequestStatus status);
}