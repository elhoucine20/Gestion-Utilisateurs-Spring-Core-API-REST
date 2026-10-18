package com.userApi.mapper;

import com.userApi.dto.UserCreateRequest;
import com.userApi.dto.UserResponse;
import com.userApi.dto.UserUpdateRequest;
import com.userApi.model.User;
import com.userApi.model.enums.RoleUser;
import com.userApi.model.enums.StatuUser;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

// recuperer les methodes assertXxx (assertEquals, assertNotNull...) au milieu de  "Assertions." a chaque fois
import static org.junit.jupiter.api.Assertions.*;

// Test de UserMapper unique
class UserMapperTest {

    private final UserMapper mapper = new UserMapper();

    @Test
    void toEntity_copieLesChampsEtMetLeStatutActive() {

        // preparation des donnees
        UserCreateRequest req = new UserCreateRequest();
        req.setName("Ali");
        req.setEmail("ali@test.com");
        req.setPassword("12345678");
        req.setRole(RoleUser.USER);

        // utiliser la methode que nous avons testerr
        User user = mapper.toEntity(req);

        // checking the result (value attendue, value reel)
        assertEquals("Ali", user.getName());
        assertEquals("ali@test.com", user.getEmail());
        assertEquals("12345678", user.getPassword());
        assertEquals(RoleUser.USER, user.getRole());
        assertEquals(StatuUser.ACTIVE, user.getStatu());
        assertNotNull(user.getCreatedAt());
    }

    @Test
    void updateEntity_modifieNomEmailRole_maisPasLePasswordNiLeStatut() {
        // cette user deja exist  avec password hash
        User user = new User("Ali", "ali@test.com", "hash", RoleUser.USER);

        // request de update des nouvelle donnees
        UserUpdateRequest req = new UserUpdateRequest();
        req.setName("Ali modifie");
        req.setEmail("nouveau@test.com");
        req.setPassword("autre-password");
        req.setRole(RoleUser.ADMIN);

        // update objet user
        mapper.updateEntity(user, req);

        // check
        assertEquals("Ali modifie", user.getName());
        assertEquals("nouveau@test.com", user.getEmail());
        assertEquals(RoleUser.ADMIN, user.getRole());
        assertEquals("hash", user.getPassword());
        assertEquals(StatuUser.ACTIVE, user.getStatu());
    }

    @Test
    void toResponse_copieLesChamps() {
        User user = new User("Ali", "ali@test.com", "hash", RoleUser.USER);
        UUID id = UUID.randomUUID();

        // ReflectionTestUtils va add valeur en champ "id" derictement, sans setter
        ReflectionTestUtils.setField(user, "id", id);

        UserResponse res = mapper.toResponse(user);

        // chaque champ dans response doit etre comme USer
        assertEquals(id, res.getId());
        assertEquals("Ali", res.getName());
        assertEquals("ali@test.com", res.getEmail());
        assertEquals(RoleUser.USER, res.getRole());
        assertEquals(StatuUser.ACTIVE, res.getStatu());
        assertNotNull(res.getCreatedAt());
    }

    @Test
        //  test classe UserResponse (security).
    void userResponse_neContientNiPasswordNiSalt() {
        // getDeclaredField("password") chercher sur champ "password" en class
        // if not exist, redereger vers NoSuchFieldException
        // assertThrows(exception attendue, code) = test nadj7 ghir ila l exception tla3at.
        assertThrows(NoSuchFieldException.class, () -> UserResponse.class.getDeclaredField("password"));
        assertThrows(NoSuchFieldException.class, () -> UserResponse.class.getDeclaredField("salt"));
    }
}