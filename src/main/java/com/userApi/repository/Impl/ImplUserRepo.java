package com.userApi.repository.Impl;

import com.userApi.model.User;
import com.userApi.model.enums.StatuUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository // pas oblegatoir
public interface ImplUserRepo extends JpaRepository<User, UUID> {
    public boolean existsByEmail(String email);
    public boolean existsByEmailAndIdNot(String email, UUID id);

    public List<User> findAllByStatu(StatuUser statu);
    public Optional<User> findByIdAndStatu(UUID id, StatuUser statu);
}
