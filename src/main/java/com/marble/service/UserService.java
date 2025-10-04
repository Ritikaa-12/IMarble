
package com.marble.service;

import com.marble.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(Integer userId, UserDto userDto);
    void deleteUser(Integer userId);
}