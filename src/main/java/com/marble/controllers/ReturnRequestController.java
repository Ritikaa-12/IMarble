package com.marble.controllers;

import com.marble.dto.ReturnRequestDto;
import com.marble.enums.ReturnRequestStatus;
import com.marble.service.ReturnRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/return-requests")
public class ReturnRequestController {

    private final ReturnRequestService returnRequestService;

    public ReturnRequestController(ReturnRequestService returnRequestService) {
        this.returnRequestService = returnRequestService;
    }

    // ---------------------- CREATE RETURN REQUEST ----------------------
    @PostMapping("/create")
    public ResponseEntity<ReturnRequestDto> createReturnRequest(@RequestBody ReturnRequestDto dto) {
        ReturnRequestDto created = returnRequestService.createReturnRequest(dto);
        return ResponseEntity.ok(created);
    }

    // ---------------------- APPROVE RETURN REQUEST ----------------------
    @PutMapping("/approve/{returnId}")
    public ResponseEntity<ReturnRequestDto> approveReturn(@PathVariable Integer returnId) {
        ReturnRequestDto updated = returnRequestService.approveReturnRequest(returnId);
        return ResponseEntity.ok(updated);
    }

    // ---------------------- REJECT RETURN REQUEST ----------------------
    @PutMapping("/reject/{returnId}")
    public ResponseEntity<ReturnRequestDto> rejectReturn(@PathVariable Integer returnId) {
        ReturnRequestDto updated = returnRequestService.rejectReturnRequest(returnId);
        return ResponseEntity.ok(updated);
    }

    // ---------------------- GET REQUESTS FOR SPECIFIC ORDER ----------------------
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ReturnRequestDto>> getRequestsForOrder(@PathVariable Integer orderId) {
        List<ReturnRequestDto> requests = returnRequestService.getRequestsForOrder(orderId);
        return ResponseEntity.ok(requests);
    }

    // ---------------------- GET REQUESTS BY STATUS ----------------------
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReturnRequestDto>> getRequestsByStatus(@PathVariable ReturnRequestStatus status) {
        List<ReturnRequestDto> list = returnRequestService.getRequestsByStatus(status);
        return ResponseEntity.ok(list);
    }
}
