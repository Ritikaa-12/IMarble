package com.marble.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marble.dto.StaffAdvancePaymentDto;
import com.marble.entities.Staff;
import com.marble.entities.StaffAdvancePayment;
import com.marble.repos.StaffAdvancePaymentRepository;
import com.marble.service.StaffAdvancePaymentService;

@Service
public class StaffAdvancePaymentServiceimpl implements StaffAdvancePaymentService {

    @Autowired
    private StaffAdvancePaymentRepository staffAdvancePaymentRepository;

    @Override
    public StaffAdvancePaymentDto saveAdvancePayment(StaffAdvancePaymentDto dto) {
        StaffAdvancePayment entity = convertToEntity(dto);
        StaffAdvancePayment saved = staffAdvancePaymentRepository.save(entity);
        return convertToDto(saved);
    }

    @Override
    public List<StaffAdvancePaymentDto> getAllAdvancePayments() {
        return staffAdvancePaymentRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StaffAdvancePaymentDto getAdvancePaymentById(Integer id) {
        return staffAdvancePaymentRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public List<StaffAdvancePaymentDto> getAdvancePaymentsByStaffId(Integer staffId) {
        return staffAdvancePaymentRepository.findByStaff_StaffId(staffId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdvancePayment(Integer id) {
        staffAdvancePaymentRepository.deleteById(id);
    }

    // ---------------------- Conversion Methods ----------------------

    private StaffAdvancePayment convertToEntity(StaffAdvancePaymentDto dto) {
        if (dto == null) return null;

        StaffAdvancePayment entity = new StaffAdvancePayment();
        entity.setAdvanceId(dto.getAdvanceId());
        entity.setAmount(dto.getAmount());
        entity.setDate(dto.getDate());
        entity.setRemarks(dto.getRemarks());
        entity.setPaymentMode(dto.getPaymentMode());

        if (dto.getStaffId() != null) {
            Staff staff = new Staff();
            staff.setStaffId(dto.getStaffId());
            entity.setStaff(staff);
        } else {
            entity.setStaff(null);
        }

        return entity;
    }

    private StaffAdvancePaymentDto convertToDto(StaffAdvancePayment entity) {
        if (entity == null) return null;

        StaffAdvancePaymentDto dto = new StaffAdvancePaymentDto();
        dto.setAdvanceId(entity.getAdvanceId());
        dto.setAmount(entity.getAmount());
        dto.setDate(entity.getDate());
        dto.setRemarks(entity.getRemarks());
        dto.setPaymentMode(entity.getPaymentMode());

        if (entity.getStaff() != null) {
            dto.setStaffId(entity.getStaff().getStaffId());
        }

        return dto;
    }
}
