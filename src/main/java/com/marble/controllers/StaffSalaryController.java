package com.marble.controllers;

import com.marble.dto.StaffSalaryDto;
import com.marble.service.StaffSalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff-salaries")
public class StaffSalaryController {

    private final StaffSalaryService staffSalaryService;

    public StaffSalaryController(StaffSalaryService staffSalaryService) {
        this.staffSalaryService = staffSalaryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<StaffSalaryDto> addSalary(@RequestBody StaffSalaryDto staffSalaryDto) {
        return new ResponseEntity<>(staffSalaryService.addSalary(staffSalaryDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<StaffSalaryDto> getSalaryById(@PathVariable Integer id) {
        return ResponseEntity.ok(staffSalaryService.getSalaryById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping
    public ResponseEntity<List<StaffSalaryDto>> getAllSalaries() {
        return ResponseEntity.ok(staffSalaryService.getAllSalaries());
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<StaffSalaryDto> updateSalary(@PathVariable Integer id, @RequestBody StaffSalaryDto staffSalaryDto) {
        return ResponseEntity.ok(staffSalaryService.updateSalary(id, staffSalaryDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable Integer id) {
        staffSalaryService.deleteSalary(id);
        return ResponseEntity.ok("Staff salary deleted successfully");
    }
}
