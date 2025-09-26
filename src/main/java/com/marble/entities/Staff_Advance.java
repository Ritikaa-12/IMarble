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
public class Staff_Advance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="advance_id")
	private Integer advanceId;
	private Integer staffId;
	private Float amount;
	private LocalDate date;
	private Boolean remarks;
	private String paymentMode;
}
