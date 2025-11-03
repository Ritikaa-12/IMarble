package com.marble.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffAttendanceDto {

    private Integer attendanceId;
    private Integer staffId;   // store only staffId instead of full Staff object
    private LocalDate date;
    private String status;
}
