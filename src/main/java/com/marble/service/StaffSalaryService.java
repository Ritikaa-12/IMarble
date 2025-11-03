package com.marble.service;

import java.util.List;
import com.marble.dto.StaffSalaryDto;

public interface StaffSalaryService {

    StaffSalaryDto addSalary(StaffSalaryDto dto);
    StaffSalaryDto updateSalary(Integer id, StaffSalaryDto dto);
    void deleteSalary(Integer id);
    StaffSalaryDto getSalaryById(Integer id);
    List<StaffSalaryDto> getAllSalaries();
}
