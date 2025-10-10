package com.marble.service;

import com.marble.dto.StaffDto;
import java.util.List;

public interface StaffService {

	StaffDto createStaffProfile(StaffDto staffDto);

	StaffDto updateStaffProfile(Integer staffId, StaffDto staffDto);

	StaffDto getStaffProfileByUserId(Integer userId);

	List<StaffDto> getAllStaff();
	
	void deleteStaff(Integer staffId);
}