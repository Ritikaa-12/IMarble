package com.marble.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.marble.dto.StaffAttendanceDto;
import com.marble.service.StaffAttendanceService;

@RestController
@RequestMapping("/attendances")
public class StaffAttendanceController {

    @Autowired
    private StaffAttendanceService attendanceService;

    @PostMapping
    public StaffAttendanceDto saveAttendance(@RequestBody StaffAttendanceDto dto) {
        return attendanceService.saveAttendance(dto);
    }

    @PutMapping("/{id}")
    public StaffAttendanceDto updateAttendance(@PathVariable Integer id, @RequestBody StaffAttendanceDto dto) {
        return attendanceService.updateAttendance(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Integer id) {
        attendanceService.deleteAttendance(id);
        return "Attendance deleted successfully!";
    }

    @GetMapping("/staff/{staffId}")
    public StaffAttendanceDto getByStaffId(@PathVariable Integer staffId) {
        return attendanceService.getAttendanceByStaffId(staffId);
    }

    @GetMapping
    public List<StaffAttendanceDto> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }
}
