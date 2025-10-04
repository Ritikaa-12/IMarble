package com.marble.dto;

import lombok.Data;

@Data
public class OrderrDto {
	 private Integer orderId;
     private Integer clientId;
     private Integer shopId;
     private Integer orderNo;
     private String items;
     private String status;
     private Float totalAmount;
}
