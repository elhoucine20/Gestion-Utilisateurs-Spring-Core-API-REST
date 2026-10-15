package com.userApi.dto;

import com.userApi.model.enums.RoleUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class UserUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank @Email
    private String email;

    @Size(min = 8)
    private String password;

    @NotNull
    private RoleUser role;
}
