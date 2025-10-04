package com.marble.dto;
import java.time.LocalDate;

import lombok.Data;

@Data

public class PurchaseEntryDto {

    private Integer purchaseEntryId;
	private Integer clientId;
	private Float totalAmount;	
	private LocalDate purchaseDate;
	private String invoiceNo;

}
