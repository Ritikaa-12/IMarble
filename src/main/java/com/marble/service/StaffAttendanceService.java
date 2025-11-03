package com.marble.service;

import java.util.List;
import com.marble.dto.StaffAttendanceDto;

public interface StaffAttendanceService {

    StaffAttendanceDto saveAttendance(StaffAttendanceDto dto);

    StaffAttendanceDto updateAttendance(Integer id, StaffAttendanceDto dto);

    void deleteAttendance(Integer id);

    StaffAttendanceDto getAttendanceByStaffId(Integer staffId);

    List<StaffAttendanceDto> getAllAttendances();
}

