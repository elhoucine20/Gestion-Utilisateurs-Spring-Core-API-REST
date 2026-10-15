package com.userApi.controller;

import com.userApi.model.User;
import com.userApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController  //
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping // simplifie @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(
            @PathVariable //
            UUID id){
        return userService.getUserById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //
    public User addUser(
            @RequestBody //
            User user){
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id,@RequestBody User user){
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }
}
