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
public class Staff_Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="attendance_id")
	private Integer attendanceId;
	
	//---------------------
	@Column(name="staff_id", nullable =false)
	private Integer staffId;
	
	@Column(name="date", nullable =false)
	private LocalDate date;
	
	@Column(name="status", nullable =false)
	private String status;
	
}
