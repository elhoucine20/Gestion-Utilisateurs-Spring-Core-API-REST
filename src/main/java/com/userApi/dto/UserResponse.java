package com.userApi.dto;

import com.userApi.model.enums.RoleUser;

import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private RoleUser role;
    private StatuUser statu;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
