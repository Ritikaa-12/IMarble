package com.marble.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.marble.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
// ... other imports

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="mobile", nullable = false, unique = true) // Mobile should be unique
	private String mobile;
	
	@Column(name="email", unique = true) // Email should also be unique
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING) // This tells JPA to store the role as a String (e.g., "ROLE_ADMIN")
	@Column(name="role", nullable = false)
	private Role role;

	@Column(name="status", nullable = false)
	private String status; // You can create an Enum for this too (e.g., ACTIVE, INACTIVE)
	
}