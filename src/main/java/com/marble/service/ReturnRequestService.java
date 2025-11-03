package com.marble.service;

import com.marble.dto.ReturnRequestDto;
import com.marble.enums.ReturnRequestStatus;
import java.util.List;

public interface ReturnRequestService {

    /**
     * Creates a new return request (e.g., from a customer).
     */
    ReturnRequestDto createReturnRequest(ReturnRequestDto returnRequestDto);

    /**
     * Approves a pending return request (e.g., by an admin).
     */
    ReturnRequestDto approveReturnRequest(Integer returnId);

    /**
     * Rejects a pending return request (e.g., by an admin).
     */
    ReturnRequestDto rejectReturnRequest(Integer returnId);

    /**
     * Gets all return requests associated with a single order.
     */
    List<ReturnRequestDto> getRequestsForOrder(Integer orderId);

    /**
     * Gets all return requests by their status (e.g., all PENDING_APPROVAL).
     */
    List<ReturnRequestDto> getRequestsByStatus(ReturnRequestStatus status);
}