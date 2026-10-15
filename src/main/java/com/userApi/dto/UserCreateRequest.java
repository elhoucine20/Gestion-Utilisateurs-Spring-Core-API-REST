package com.userApi.dto;

import com.userApi.model.enums.RoleUser;
import lombok.Data;

@Data
public class UserCreateRequest {
    private String name;
    private String email;
    private String password;
    private RoleUser role;
}
