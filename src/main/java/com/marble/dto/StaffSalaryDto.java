package com.marble.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StaffSalaryDto {
    private Integer salaryId;
    private Integer staffId;
    private String month;
    private String year;
    private Float amount;
    private LocalDate paymentDate;
    private String paymentMode;
    private String description;
}

