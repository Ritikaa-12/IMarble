package com.marble.dto;

import lombok.Data;

@Data
public class ClientDto {

private Integer clientId;
	
	private String name;
	private String mobile;
	private String email;
	private String address;
	private String type;// (purchaseDealer,sellsDealer,client)
	private Integer gstNo;
}
