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
public class StaffAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="attendance_id")
	private Integer attendanceId;
	
	// one staff person   - One Attendance //  ASk++++++++++++++++++++Chatg 
    @OneToOne
    @JoinColumn(name="staff_id",nullable=false)
    private Staff staff;
	
	@Column(name="date", nullable =false)
	private LocalDate date;
	
	@Column(name="status", nullable =false)//(Absent/Half-Day)
	private String status;
	
}
