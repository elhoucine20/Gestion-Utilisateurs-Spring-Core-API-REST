package com.userApi.dto;

import com.userApi.model.enums.RoleUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
public class UserCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank @Size(min = 8)
    private String password;

    @NotNull
    private RoleUser role;
}
