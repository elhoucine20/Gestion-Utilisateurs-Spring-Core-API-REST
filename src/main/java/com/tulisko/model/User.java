package com.tulisko.model;

import com.tulisko.model.enums.RoleUser;
import com.tulisko.model.enums.StatuUser;

import java.time.LocalDate;
import java.util.UUID;

public class User {

    private UUID id;
    private  String name;
    private String email;
    private String password;
    private RoleUser role;
    private StatuUser statu;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public User(String name, String email, String password, RoleUser role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.statu = StatuUser.ACTIVE;
        this.createdAt = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public StatuUser isActive() {
        return statu;
    }

    public void setActive(StatuUser statu) {
        this.statu = statu;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
