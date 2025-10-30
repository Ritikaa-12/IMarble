package com.marble.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marble.dto.StaffSalaryDto;
import com.marble.entities.Staff;
import com.marble.entities.StaffSalary;
import com.marble.repos.StaffRepository;
import com.marble.repos.StaffSalaryRepository;
import com.marble.service.StaffSalaryService;

@Service
public class StaffSalaryServiceImpl implements StaffSalaryService {

    @Autowired
    private StaffSalaryRepository salaryRepository;

    @Autowired
    private StaffRepository staffRepository; 

    // ------------------ DTO → Entity ------------------
    private StaffSalary convertToEntity(StaffSalaryDto dto) {
        StaffSalary entity = new StaffSalary();
        entity.setSalaryId(dto.getSalaryId());

        if (dto.getStaffId() != null) {
            Staff staff = staffRepository.findById(dto.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + dto.getStaffId()));
            entity.setStaff(staff);
        }

        entity.setMonth(dto.getMonth());
        entity.setYear(dto.getYear());
        entity.setAmount(dto.getAmount());
        entity.setPaymentDate(dto.getPaymentDate());
        entity.setPaymentMode(dto.getPaymentMode());
        entity.setDescription(dto.getDescription());

        return entity;
    }

    // ------------------ Entity → DTO ------------------
    private StaffSalaryDto convertToDto(StaffSalary entity) {
        StaffSalaryDto dto = new StaffSalaryDto();

        dto.setSalaryId(entity.getSalaryId());
        dto.setStaffId(entity.getStaff() != null ? entity.getStaff().getStaffId() : null);
        dto.setMonth(entity.getMonth());
        dto.setYear(entity.getYear());
        dto.setAmount(entity.getAmount());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setPaymentMode(entity.getPaymentMode());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    // ------------------ CRUD Methods ------------------

    @Override
    public StaffSalaryDto addSalary(StaffSalaryDto dto) {
        StaffSalary entity = convertToEntity(dto);
        StaffSalary saved = salaryRepository.save(entity);
        return convertToDto(saved);
    }

    @Override
    public StaffSalaryDto updateSalary(Integer id, StaffSalaryDto dto) {
        StaffSalary existing = salaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary not found with ID: " + id));

        if (dto.getStaffId() != null) {
            Staff staff = staffRepository.findById(dto.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + dto.getStaffId()));
            existing.setStaff(staff);
        }

        existing.setMonth(dto.getMonth());
        existing.setYear(dto.getYear());
        existing.setAmount(dto.getAmount());
        existing.setPaymentDate(dto.getPaymentDate());
        existing.setPaymentMode(dto.getPaymentMode());
        existing.setDescription(dto.getDescription());

        StaffSalary updated = salaryRepository.save(existing);
        return convertToDto(updated);
    }

    @Override
    public void deleteSalary(Integer id) {
        if (!salaryRepository.existsById(id)) {
            throw new RuntimeException("Salary not found with ID: " + id);
        }
        salaryRepository.deleteById(id);
    }

    @Override
    public StaffSalaryDto getSalaryById(Integer id) {
        StaffSalary salary = salaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary not found with ID: " + id));
        return convertToDto(salary);
    }

    @Override
    public List<StaffSalaryDto> getAllSalaries() {
        return salaryRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
