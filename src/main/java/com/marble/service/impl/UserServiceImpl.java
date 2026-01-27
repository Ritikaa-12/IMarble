package com.marble.service.impl;

import com.marble.dto.UserDto;
import com.marble.entities.Users;
import com.marble.repos.UserRepository;
import com.marble.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        Users user = this.dtoToEntity(userDto);

        // Encode password before saving
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

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

        // If a new password is provided, encode it before setting
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        Users updatedUser = userRepository.save(user);
        return this.entityToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
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

    // New method to authenticate user with mobile and raw password
    @Override
    public boolean authenticate(String mobile, String rawPassword) {
        Optional<Users> optionalUser = userRepository.findByMobile(mobile);
        if (optionalUser.isEmpty()) {
            return false;
        }
        Users user = optionalUser.get();
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }


    // Helper methods to convert between Entity and DTO

    private UserDto entityToDto(Users user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setMobile(user.getMobile());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        // Don't set password in DTO for security reasons
        return dto;
    }

    private Users dtoToEntity(UserDto userDto) {
        Users user = new Users();
        user.setName(userDto.getName());
        user.setMobile(userDto.getMobile());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setStatus(userDto.getStatus());
        // Do NOT set password here to avoid confusion — encode and set password explicitly in createUser and updateUser
        return user;
    }
}
