package com.marble.dto;

import com.marble.enums.ReturnRequestStatus;
import com.marble.enums.ReturnRequestType;
import lombok.Data;

@Data
public class ReturnRequestDto {
    private Integer returnId;
    private Integer quantity;
    private ReturnRequestType type;
    private String description;
    private ReturnRequestStatus status;

    // IDs for creating the request
    private Integer orderId;
    private Integer orderItemId;

    // Extra details for display in a dashboard
    private String productName;
}