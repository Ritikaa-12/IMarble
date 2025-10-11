package com.marble.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer staffId;

    //  One Staff profile belongs to one User.
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user; 
	
	@Column(name="address")
	private String address;
	
	@Column(name="joining_date")
	private LocalDate joiningDate;
	
	@Column(name="leaving_date",nullable=true)
	private LocalDate leavingDate;
	
	@Column(name="salary_type") // Daily/Monthly
	private String salaryType;
	
	@Column(name="base_salary")
	private Double baseSalary; // salary double me hogi string me nahi
	
    @ManyToOne
    @JoinColumn(name = "shop_id") 
    private Shop shop;
}
