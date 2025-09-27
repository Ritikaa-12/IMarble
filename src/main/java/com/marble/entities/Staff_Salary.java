package com.marble.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Staff_Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="salary_id")
	private Integer salaryId;
	
	//several times- salary belong to one staff person // ASk+++++++++++++Chat G
	@OneToOne
	@JoinColumn(name="staff_id",nullable=false)
	private Staff staff;
	
	@Column(name="month", nullable =false)
	private String month;
	
	@Column(name="year", nullable =false)
	private String year;
	
	@Column(name="amount", nullable =false)
	private Float amount;
	
	@Column(name="payment_date", nullable =false)
	private LocalDate paymentDate;
	
	@Column(name="payment_mode", nullable =false)
	private String paymentMode;
	
	@Column(name="description", nullable =false)
	private String description;
}
