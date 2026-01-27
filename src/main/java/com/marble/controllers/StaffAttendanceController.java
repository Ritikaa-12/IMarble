package com.marble.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.marble.dto.StaffAttendanceDto;
import com.marble.service.StaffAttendanceService;

@RestController
@RequestMapping("/attendances")
public class StaffAttendanceController {

    @Autowired
    private StaffAttendanceService attendanceService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public StaffAttendanceDto saveAttendance(@RequestBody StaffAttendanceDto dto) {
        return attendanceService.saveAttendance(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/{id}")
    public StaffAttendanceDto updateAttendance(@PathVariable Integer id, @RequestBody StaffAttendanceDto dto) {
        return attendanceService.updateAttendance(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Integer id) {
        attendanceService.deleteAttendance(id);
        return "Attendance deleted successfully!";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping("/staff/{staffId}")
    public StaffAttendanceDto getByStaffId(@PathVariable Integer staffId) {
        return attendanceService.getAttendanceByStaffId(staffId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping
    public List<StaffAttendanceDto> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }
}
