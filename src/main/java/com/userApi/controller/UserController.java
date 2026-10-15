package com.userApi.controller;

import com.userApi.dto.UserCreateRequest;
import com.userApi.dto.UserResponse;
import com.userApi.dto.UserUpdateRequest;
import com.userApi.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController  //
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping // simplifie @RequestMapping(method = RequestMethod.GET)
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(
            @PathVariable //
            UUID id){
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //
    public UserResponse addUser(
            @Valid
            @RequestBody //
            UserCreateRequest user){
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateRequest user){
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }
}
