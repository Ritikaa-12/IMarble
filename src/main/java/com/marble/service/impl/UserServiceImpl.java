package com.marble.service.impl;

import com.marble.dto.UserDto;
import com.marble.entities.Users;
import com.marble.repos.UserRepository;
import com.marble.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

 
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        
        Users user = this.dtoToEntity(userDto);
        
        
        Users savedUser = userRepository.save(user);
      
        return this.entityToDto(savedUser);
    }
    
    @Override
    public UserDto updateUser(Integer userId, UserDto userDto) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        
        user.setName(userDto.getName());
        user.setMobile(userDto.getMobile());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setStatus(userDto.getStatus());
        
        // If a new password is provided, it is set directly in plain text.
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(userDto.getPassword());
        }

        Users updatedUser = userRepository.save(user);
        return this.entityToDto(updatedUser);
    }
    
    @Override
    public UserDto getUserById(Integer userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not now found"));
        return this.entityToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::entityToDto) 
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    
    private UserDto entityToDto(Users user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setMobile(user.getMobile());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        return dto;
    }


    private Users dtoToEntity(UserDto userDto) {
        Users user = new Users();
        user.setName(userDto.getName());
        user.setMobile(userDto.getMobile());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setStatus(userDto.getStatus());
    
        user.setPassword(userDto.getPassword());
        return user;
    }
}