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
public class Dealer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dealer_id")
     private Integer dealerId;
	
     private String type;//(Purchase / seller)
     private String name;
     private String mobile;
     @Column(nullable = true)
     private String email;
     private String address;
     //ask++++++++++++++++++++++
     private Integer gstNo;
}
