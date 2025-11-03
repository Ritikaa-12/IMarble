package com.marble.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marble.dto.StaffAttendanceDto;
import com.marble.entities.Staff;
import com.marble.entities.StaffAttendance;
import com.marble.repos.StaffAttendanceRepository;
import com.marble.repos.StaffRepository;
import com.marble.service.StaffAttendanceService;

@Service
public class StaffAttendanceServiceImpl implements StaffAttendanceService {

    @Autowired
    private StaffAttendanceRepository attendanceRepository;

    @Autowired
    private StaffRepository staffRepository;

    private StaffAttendance convertToEntity(StaffAttendanceDto dto) {
        StaffAttendance entity = new StaffAttendance();
        entity.setAttendanceId(dto.getAttendanceId());
        entity.setDate(dto.getDate());
        entity.setStatus(dto.getStatus());

        if (dto.getStaffId() != null) {
            Staff staff = staffRepository.findById(dto.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + dto.getStaffId()));
            entity.setStaff(staff);
        }

        return entity;
    }

    private StaffAttendanceDto convertToDto(StaffAttendance entity) {
        StaffAttendanceDto dto = new StaffAttendanceDto();
        dto.setAttendanceId(entity.getAttendanceId());
        dto.setDate(entity.getDate());
        dto.setStatus(entity.getStatus());
        dto.setStaffId(entity.getStaff() != null ? entity.getStaff().getStaffId() : null);
        return dto;
    }

    @Override
    public StaffAttendanceDto saveAttendance(StaffAttendanceDto dto) {
        StaffAttendance saved = attendanceRepository.save(convertToEntity(dto));
        return convertToDto(saved);
    }

    @Override
    public StaffAttendanceDto updateAttendance(Integer id, StaffAttendanceDto dto) {
        StaffAttendance existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with ID: " + id));

        existing.setDate(dto.getDate());
        existing.setStatus(dto.getStatus());

        if (dto.getStaffId() != null) {
            Staff staff = staffRepository.findById(dto.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + dto.getStaffId()));
            existing.setStaff(staff);
        }

        return convertToDto(attendanceRepository.save(existing));
    }

    @Override
    public void deleteAttendance(Integer id) {
        attendanceRepository.deleteById(id);
    }

    @Override
    public StaffAttendanceDto getAttendanceByStaffId(Integer staffId) {
        StaffAttendance attendance = attendanceRepository.findByStaff_StaffId(staffId)
                .orElseThrow(() -> new RuntimeException("Attendance not found for staff ID: " + staffId));
        return convertToDto(attendance);
    }

    @Override
    public List<StaffAttendanceDto> getAllAttendances() {
        return attendanceRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
