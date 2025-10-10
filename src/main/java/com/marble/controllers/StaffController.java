package com.marble.controllers;

import com.marble.dto.StaffDto;
import com.marble.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<StaffDto> createStaffProfile(@RequestBody StaffDto staffDto) {
        return new ResponseEntity<>(staffService.createStaffProfile(staffDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> getStaffProfileByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(staffService.getStaffProfileByUserId(id));
    }

    @GetMapping
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDto> updateStaffProfile(@PathVariable Integer id, @RequestBody StaffDto staffDto) {
        return ResponseEntity.ok(staffService.updateStaffProfile(id, staffDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Integer id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok("Staff deleted successfully");
    }
}