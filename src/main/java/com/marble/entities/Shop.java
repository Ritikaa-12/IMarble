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
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="shop_id")
	private Integer shopId;
	
	@Column(name="title", nullable =false)
	private String title;
	
	@Column(name="location", nullable =false)
	private String location;
	
	@Column(name="owner_id", nullable =false)
	private Integer ownerId;
	
	@Column(name="status", nullable =false)
	private String status;
}
