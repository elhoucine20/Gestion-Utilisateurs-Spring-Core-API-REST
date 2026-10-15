package com.userApi.mapper;

import com.userApi.dto.UserCreateRequest;
import com.userApi.dto.UserResponse;
import com.userApi.dto.UserUpdateRequest;
import com.userApi.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateRequest request){
        return new User(request.getName(), request.getEmail(), request.getPassword(),request.getRole());
    }

    public void updateEntity(User user, UserUpdateRequest request){
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
    }

    public UserResponse toResponse(User u){
        return new UserResponse(u.getId(),u.getName(),u.getEmail(),u.getRole(),u.getStatu(),u.getCreatedAt(),u.getUpdatedAt());
    }
}
