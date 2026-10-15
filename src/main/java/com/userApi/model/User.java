package com.userApi.model;

import com.userApi.model.enums.RoleUser;
import com.userApi.model.enums.StatuUser;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUser role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatuUser statu;

    @Column(nullable = false)
    private LocalDate createdAt;

    private LocalDate updatedAt;

    @Column(nullable = false)
    private String salt;

    public User(String name, String email, String password, RoleUser role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.statu = StatuUser.ACTIVE;
        this.createdAt = LocalDate.now();
    }

    public User(){}

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


    @PrePersist
    public void onCreate(){
        if (this.statu == null) this.statu = StatuUser.ACTIVE;
        if (this.createdAt == null) this.createdAt = LocalDate.now();
    }
    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDate.now();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public StatuUser getStatu() {
        return statu;
    }

    public void setStatu(StatuUser statu) {
        this.statu = statu;
    }
}

