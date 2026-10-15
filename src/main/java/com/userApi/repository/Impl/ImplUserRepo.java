package com.userApi.repository.Impl;

import com.userApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository // pas oblegatoir
public interface ImplUserRepo extends JpaRepository<User, UUID> {
    public boolean existsByEmail(String email);
    public boolean existsByEmailAndIdNot(String email, UUID id);
}
