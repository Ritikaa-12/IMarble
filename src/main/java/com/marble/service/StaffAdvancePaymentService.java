package com.marble.service;

import java.util.List;
import com.marble.dto.StaffAdvancePaymentDto;

public interface StaffAdvancePaymentService {
    StaffAdvancePaymentDto saveAdvancePayment(StaffAdvancePaymentDto dto);
    List<StaffAdvancePaymentDto> getAllAdvancePayments();
    StaffAdvancePaymentDto getAdvancePaymentById(Integer id);
    List<StaffAdvancePaymentDto> getAdvancePaymentsByStaffId(Integer staffId);
    void deleteAdvancePayment(Integer id);
}
