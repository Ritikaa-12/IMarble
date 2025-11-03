package com.marble.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StaffAdvancePaymentDto {
private Integer advanceId;
	
    private Integer staffId;  
	private Float amount;
	private LocalDate date;
	private Boolean remarks; // advance payment done or not
	private String paymentMode;
}
