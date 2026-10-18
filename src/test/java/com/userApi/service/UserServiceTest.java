package com.userApi.service;

import com.userApi.dto.UserCreateRequest;
import com.userApi.dto.UserResponse;
import com.userApi.dto.UserUpdateRequest;
import com.userApi.mapper.UserMapper;
import com.userApi.model.User;
import com.userApi.model.enums.RoleUser;
import com.userApi.model.enums.StatuUser;
import com.userApi.repository.Impl.ImplUserRepo;
import com.userApi.util.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class) // indique a speing utilise mockito avant de chaque test et fixe les annotations @Mock
public class UserServiceTest {

    @Mock
    private ImplUserRepo userRepo;
    @Mock
    private PasswordUtils passwordUtils;
    @Mock
    private  UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    // create un user pour l'optimisation de code
    private User OneUser(){
        return new User("lahcen","lahcen@gmail.com","12345678", RoleUser.USER);
    }


    private UserResponse OneResponse(){
        return new UserResponse(UUID.randomUUID(),"lahcen","lahcen@gmail.com",
                RoleUser.USER, StatuUser.ACTIVE, LocalDate.now(),null);
    }

    // add user
    @Test
    void addUser_valide_hashLePasswordEtSauvegarde(){
        UserCreateRequest request = new UserCreateRequest();
        request.setName("lahcen");
        request.setEmail("lahcen@gmail.com");
        request.setPassword("12345678");
        request.setRole(RoleUser.USER);
        User user = OneUser(); // recuperer user
        UserResponse response = OneResponse(); // recuperer response

        when(userMapper.toEntity(request)).thenReturn(user);
        when(passwordUtils.generateSalt()).thenReturn("salt123");
        when(passwordUtils.hashPassword("12345678", "salt123")).thenReturn("hash123");
        when(userRepo.save(user)).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(response);
        //mockito return false by default (if email n'exist pas)

        // act ()
        UserResponse result = userService.addUser(request);

        // assert  (pour verifiy)
        assertSame(response,result); // compare return de service avec mapper
        assertEquals("salt123",user.getSalt()); // verify salt
        assertEquals("hash123",user.getPassword()); // verify password
        verify(userRepo).save(user); // verify save
    }

    @Test
    void addUser_emailDejaUtilise_409EtPassDeSauvgarde(){
        // ARRANGE
        UserCreateRequest request = new UserCreateRequest();
        request.setEmail("lahcen@gmail.com");
        // DB => email deja exist
        when(userRepo.existsByEmail("lahcen@gmail.com")).thenReturn(true);

        // ACT + ASSERT : recuperer et return exception
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,()->userService.addUser(request));
        assertEquals(HttpStatus.CONFLICT,exception.getStatusCode());

        verify(userRepo,never()).save(any());
    }

    // ================= getUserById =================
    @Test
    void getUserById_existantReturnReponse(){
        UUID id = UUID.randomUUID();
        User user = OneUser();
        UserResponse response = OneResponse();
        when(userRepo.findByIdAndStatu(id,StatuUser.ACTIVE)).thenReturn(Optional.of(user));
        when(userMapper.toResponse(user)).thenReturn(response);
        assertSame(response,userService.getUserById(id));
    }

    @Test
    void getUserById_inexistantOuDesactive_404(){
        UUID id = UUID.randomUUID();
        when(userRepo.findByIdAndStatu(id, StatuUser.ACTIVE)).thenReturn(Optional.empty());
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,() -> userService.getUserById(id));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());

    }

    // ================= getUsers =================
    @Test
    void geUsers_returnSeulementLesActifs(){
        User user = OneUser();
        UserResponse response = OneResponse();
        when(userRepo.findAllByStatu(StatuUser.ACTIVE)).thenReturn(List.of(user));
        when(userMapper.toResponse(user)).thenReturn(response);

        List<UserResponse> result = userService.getUsers();
        assertEquals(1,result.size());
        assertSame(response,result.get(0));
        // sans findAll
        verify(userRepo,never()).findAll();
    }

    // ================= updateUser =================
    @Test
    void updateUser_sansPassword_garde_lAncienPassword(){
        UUID id = UUID.randomUUID();
        User exist = OneUser();
        exist.setPassword("hashAncien");
        exist.setSalt("saltAncien");

        UserResponse response = OneResponse();
        UserUpdateRequest request = new UserUpdateRequest();
        request.setName("lahcen udate");
        request.setEmail("lahcenupdate@gmail.com");
        request.setRole(RoleUser.USER);
        when(userRepo.findByIdAndStatu(id,StatuUser.ACTIVE)).thenReturn(Optional.of(exist));
        when(userRepo.save(exist)).thenReturn(exist);
        when(userMapper.toResponse(exist)).thenReturn(response);

        userService.updateUser(id,request);
        assertEquals("hashAncien",exist.getPassword());
        verify(userMapper).updateEntity(exist,request);
        verify(passwordUtils,never()).hashPassword(any(),any());
    }

    @Test
    void updateUser_avecPassword_hashAvecLeSaltDeLUtilisateurExistant(){
        UUID id = UUID.randomUUID();
        User exist = OneUser();
        exist.setPassword("hashAncien");
        exist.setSalt("saltAncien");

        UserUpdateRequest req = new UserUpdateRequest();
        req.setName("lahcen");
        req.setEmail("lahcen@gmail.com");
        req.setPassword("123456789");
        req.setRole(RoleUser.USER);

        when(userRepo.findByIdAndStatu(id, StatuUser.ACTIVE)).thenReturn(Optional.of(exist));
        when(passwordUtils.hashPassword("123456789", "saltAncien")).thenReturn("nouveauHash");
        when(userRepo.save(exist)).thenReturn(exist);

        userService.updateUser(id, req);
        assertEquals("nouveauHash", exist.getPassword());
    }

    @Test
    void updateUser_emailPrisParUnAutre_409(){
        UUID id = UUID.randomUUID();
        //User exist = OneUser();
        UserUpdateRequest request = new UserUpdateRequest();
        request.setEmail("lahcen2@gmail.com");
        //when(userRepo.findByIdAndStatu(id,StatuUser.ACTIVE)).thenReturn(Optional.of(exist));
        when(userRepo.existsByEmailAndIdNot("lahcen2@gmail.com",id)).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,()->userService.updateUser(id,request));
        assertEquals(HttpStatus.CONFLICT,exception.getStatusCode());
        verify(userRepo,never()).save(any());
    }

    @Test
    void updateUser_inexistant_404(){
        UUID id = UUID.randomUUID();
        when(userRepo.findByIdAndStatu(id,StatuUser.ACTIVE)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                ()->userService.updateUser(id,new UserUpdateRequest()));
        assertEquals(HttpStatus.NOT_FOUND,exception.getStatusCode());
        verify(userRepo,never()).save(any());
    }

    // ================= deleteUser (soft delete) =================
    @Test
    void deleteUser_desactiveLUtilisateurSansLeSupprimer(){
        UUID id = UUID.randomUUID();
        User exist = OneUser();
        when(userRepo.findByIdAndStatu(id,StatuUser.ACTIVE)).thenReturn(Optional.of(exist));
        userService.deleteUser(id);
        assertEquals(StatuUser.DESACTIVE,exist.getStatu()); // change statu
        verify(userRepo).save(exist);
        verify(userRepo,never()).deleteById(any()); // soft delete
    }

    @Test
    void deleteUser_inexistant_404(){
        UUID id = UUID.randomUUID();
        when(userRepo.findByIdAndStatu(id,StatuUser.ACTIVE)).thenReturn(Optional.empty());
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,()->userService.deleteUser(id));
        assertEquals(HttpStatus.NOT_FOUND,exception.getStatusCode());
        verify(userRepo,never()).save(any());
    }

}
