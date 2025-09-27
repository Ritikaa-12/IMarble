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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="name", nullable =false)
	private String name;
	
	@Column(name="mobile", nullable =false)
	private String mobile;
	
	@Column(name="email", nullable =true)
	private String email;
	
	@Column(name="password", nullable =false)
	private String password;
	
	@Column(name="role", nullable =false)
	private String role;//role : Manager , Dispatcher , Recepionist , Admin

	
	@Column(name="status", nullable =false)
	private String status;
	
}
