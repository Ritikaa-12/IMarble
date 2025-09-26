package com.marble.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Client {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="client_id")
	private Integer clientId;
	
	private String name;
	private String mobile;
	private String email;
	private String address;
	//ask++++++++++++++++++++++++++++++++++
	@Column(nullable = true)
	private Integer gstNo;
}
