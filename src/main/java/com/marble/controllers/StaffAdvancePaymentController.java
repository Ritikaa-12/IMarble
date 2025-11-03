package com.marble.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.marble.dto.StaffAdvancePaymentDto;
import com.marble.service.StaffAdvancePaymentService;

@RestController
@RequestMapping("/advance-payments")
public class StaffAdvancePaymentController {

    @Autowired
    private StaffAdvancePaymentService staffAdvancePaymentService;

    @PostMapping
    public ResponseEntity<StaffAdvancePaymentDto> addAdvancePayment(@RequestBody StaffAdvancePaymentDto dto) {
        StaffAdvancePaymentDto savedPayment = staffAdvancePaymentService.saveAdvancePayment(dto);
        return ResponseEntity.ok(savedPayment);
    }

    @GetMapping
    public ResponseEntity<List<StaffAdvancePaymentDto>> getAllAdvancePayments() {
        List<StaffAdvancePaymentDto> payments = staffAdvancePaymentService.getAllAdvancePayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffAdvancePaymentDto> getAdvancePaymentById(@PathVariable Integer id) {
        StaffAdvancePaymentDto payment = staffAdvancePaymentService.getAdvancePaymentById(id);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<StaffAdvancePaymentDto>> getAdvancePaymentsByStaffId(@PathVariable Integer staffId) {
        List<StaffAdvancePaymentDto> payments = staffAdvancePaymentService.getAdvancePaymentsByStaffId(staffId);
        return ResponseEntity.ok(payments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffAdvancePaymentDto> updateAdvancePayment(
            @PathVariable Integer id,
            @RequestBody StaffAdvancePaymentDto dto) {

        StaffAdvancePaymentDto existing = staffAdvancePaymentService.getAdvancePaymentById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        dto.setAdvanceId(id); // ensure ID is set
        StaffAdvancePaymentDto updated = staffAdvancePaymentService.saveAdvancePayment(dto);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvancePayment(@PathVariable Integer id) {
        StaffAdvancePaymentDto existing = staffAdvancePaymentService.getAdvancePaymentById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        staffAdvancePaymentService.deleteAdvancePayment(id);
        return ResponseEntity.noContent().build();
    }
}
