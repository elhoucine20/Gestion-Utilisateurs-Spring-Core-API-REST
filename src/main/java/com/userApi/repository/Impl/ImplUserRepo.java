package com.userApi.repository.Impl;

import com.userApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImplUserRepo extends JpaRepository<User, UUID> {}
