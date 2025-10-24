package com.marble.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StaffAttendanceDto {

	private Integer attendanceId;
    private Integer staff;
	private LocalDate date;
	private String status;
}
