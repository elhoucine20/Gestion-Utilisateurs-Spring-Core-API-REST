package com.userApi.service;

import com.userApi.model.User;
import com.userApi.repository.Impl.ImplUserRepo;
import com.userApi.util.PasswordUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ImplUserRepo  userRepo;
    private final PasswordUtils passwordUtils;


    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User getUserById(UUID userId){
        return userRepo.findById(userId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found !!"));
    }

    public User addUser(User user){
        user.setSalt(passwordUtils.generateSalt());
        user.setPassword(passwordUtils.hashPassword(user.getPassword(), user.getSalt()));
        return userRepo.save(user);
    }
    public User updateUser(UUID id,User user){
        User exist = getUserById(id);
        exist.setName(user.getName());
        exist.setEmail(user.getEmail());
        exist.setPassword(passwordUtils.hashPassword(user.getPassword(), exist.getSalt()));
        exist.setRole(user.getRole());
        return userRepo.save(exist);
    }

    public void deleteUser(UUID userId){
        userRepo.deleteById(userId);
    }
}
