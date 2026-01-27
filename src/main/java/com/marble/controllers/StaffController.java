package com.marble.controllers;

import com.marble.dto.StaffDto;
import com.marble.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<StaffDto> createStaffProfile(@RequestBody StaffDto staffDto) {
        return new ResponseEntity<>(staffService.createStaffProfile(staffDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> getStaffProfileByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(staffService.getStaffProfileByUserId(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','RECEPTIONIST')")
    @GetMapping
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<StaffDto> updateStaffProfile(@PathVariable Integer id, @RequestBody StaffDto staffDto) {
        return ResponseEntity.ok(staffService.updateStaffProfile(id, staffDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Integer id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok("Staff deleted successfully");
    }
}