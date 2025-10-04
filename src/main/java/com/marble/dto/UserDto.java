
package com.marble.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marble.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private Integer userId;
    private String name;
    private String mobile;
    private String email;
    private Role role;
    private String status;

   
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}