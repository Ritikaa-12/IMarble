package com.marble.entities;

import java.time.LocalDate;

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
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="staff_id")
	private Integer staffId;
	
	@Column(name="user_id", nullable =false)
	private Integer userId;
	
	@Column(name="name", nullable =false)
	private String name;
	
	@Column(name="role", nullable =false)
	private String role;
	
	@Column(name="mobile", nullable =false)
	private String mobile;
	
	@Column(name="address", nullable =false)
	private String address;
	
	@Column(name="joining_date", nullable =false)
	private LocalDate joiningDate;
	
	@Column(name="leaving_date", nullable =false)
	private LocalDate leavingDate;
	
	@Column(name="salary_type", nullable =false)
	private String salaryType;
	
	@Column(name="base_salary", nullable =false)
	private String base_salary;
	
	private Integer shopId;
}
