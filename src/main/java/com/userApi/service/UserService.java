package com.userApi.service;

import com.userApi.dto.UserCreateRequest;
import com.userApi.dto.UserResponse;
import com.userApi.dto.UserUpdateRequest;
import com.userApi.mapper.UserMapper;
import com.userApi.model.User;
import com.userApi.model.enums.StatuUser;
import com.userApi.repository.Impl.ImplUserRepo;
import com.userApi.util.PasswordUtils;
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
    private final UserMapper userMapper;


    private User findUser(UUID id){
        return userRepo.findByIdAndStatu(id, StatuUser.ACTIVE)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found !!"));
    }

    public List<UserResponse> getUsers(){
        return userRepo.findAllByStatu(StatuUser.ACTIVE).stream().map(userMapper::toResponse).toList();
    }

    public UserResponse getUserById(UUID userId){
        return userMapper.toResponse(findUser(userId));
    }

    public UserResponse addUser(UserCreateRequest request){
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email deja utilise");
        }
        User user = userMapper.toEntity(request);
        user.setSalt(passwordUtils.generateSalt());
        user.setPassword(passwordUtils.hashPassword(user.getPassword(), user.getSalt()));
        return userMapper.toResponse(userRepo.save(user));
    }
    public UserResponse updateUser(UUID id, UserUpdateRequest request){
        if (userRepo.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email deja utilise");
        }
        User exist = findUser(id);
        userMapper.updateEntity(exist,request);
        if (request.getPassword() != null && !request.getPassword().isBlank()){
            exist.setPassword(passwordUtils.hashPassword(request.getPassword(), exist.getSalt()));
        }
        return userMapper.toResponse(userRepo.save(exist));
    }

    public void deleteUser(UUID userId){
        User user = findUser(userId);
        user.setStatu(StatuUser.DESACTIVE);
        userRepo.save(user);
    }
}
