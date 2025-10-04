package com.marble.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DeliveryDto {
    private Integer deliveryId;
    private String status;
    private LocalDate date;
    private String deliveryAddress;

  
    private Integer orderId;
    private Integer staffId;
}