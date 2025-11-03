package com.marble.dto;

import com.marble.enums.Role;
import lombok.Data;
import java.time.LocalDate;

@Data
public class StaffDto {
    // Fields from the linked User entity
    private Integer userId;
    private String name;
    private String mobile;
    private String email;
    private Role role;

    // Fields from the Staff entity itself
    private Integer staffId;
    private String address;
    private LocalDate joiningDate;
    private LocalDate leavingDate;
    private String salaryType; 
    private Double baseSalary;
    private Integer shopId;
}