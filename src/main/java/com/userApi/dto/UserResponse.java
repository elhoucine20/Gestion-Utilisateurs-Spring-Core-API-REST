package com.userApi.dto;

import com.userApi.model.enums.RoleUser;
import com.userApi.model.enums.StatuUser;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private RoleUser role;
    private StatuUser statu;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
