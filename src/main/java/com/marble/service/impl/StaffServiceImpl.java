package com.marble.service.impl;

import com.marble.dto.StaffDto;
import com.marble.entities.Shop;
import com.marble.entities.Staff;
import com.marble.entities.Users;
import com.marble.repos.ShopRepository;
import com.marble.repos.StaffRepository;
import com.marble.repos.UserRepository;
import com.marble.service.StaffService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final UserRepository usersRepository;
    private final ShopRepository shopRepository;

    public StaffServiceImpl(StaffRepository staffRepository, UserRepository usersRepository, ShopRepository shopRepository) {
        this.staffRepository = staffRepository;
        this.usersRepository = usersRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    public StaffDto createStaffProfile(StaffDto staffDto) {
        
        Users user = usersRepository.findById(staffDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        
        Shop shop = shopRepository.findById(staffDto.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        Staff staff = new Staff();
        staff.setUser(user);
        staff.setShop(shop);
        staff.setAddress(staffDto.getAddress());
        staff.setJoiningDate(staffDto.getJoiningDate());
        staff.setSalaryType(staffDto.getSalaryType());
        staff.setBaseSalary(staffDto.getBaseSalary());

        Staff savedStaff = staffRepository.save(staff);
        return entityToDto(savedStaff);
    }

    @Override
    public StaffDto updateStaffProfile(Integer staffId, StaffDto staffDto) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff profile not found"));
        
        staff.setAddress(staffDto.getAddress());
        staff.setLeavingDate(staffDto.getLeavingDate());
        staff.setSalaryType(staffDto.getSalaryType());
        staff.setBaseSalary(staffDto.getBaseSalary());

        Staff updatedStaff = staffRepository.save(staff);
        return entityToDto(updatedStaff);
    }

    @Override
    public StaffDto getStaffProfileByUserId(Integer userId) {
        Staff staff = staffRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Staff profile not found for this user"));
        return entityToDto(staff);
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return staffRepository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    
    private StaffDto entityToDto(Staff staff) {
        Users user = staff.getUser();
        Shop shop = staff.getShop();
        
        StaffDto dto = new StaffDto();
        
    
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setMobile(user.getMobile());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

    
        dto.setStaffId(staff.getStaffId());
        dto.setAddress(staff.getAddress());
        dto.setJoiningDate(staff.getJoiningDate());
        dto.setLeavingDate(staff.getLeavingDate());
        dto.setSalaryType(staff.getSalaryType());
        dto.setBaseSalary(staff.getBaseSalary());
        
        if (shop != null) {
            dto.setShopId(shop.getShopId());
        }

        return dto;
    }

	@Override
	public void deleteStaff(Integer staffId) {
		Staff staff=staffRepository.findById(staffId).orElseThrow(() -> new RuntimeException("Staff not found with ID"+ staffId));
    	staffRepository.delete(staff);
		
	}
}