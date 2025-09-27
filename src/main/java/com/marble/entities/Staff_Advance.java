package com.marble.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	// Many advance payments can belong to one Staff member
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false) 
    private Staff staff;
    
	private Float amount;
	private LocalDate date;
	private Boolean remarks; // advance payment done or not
	private String paymentMode;
}
