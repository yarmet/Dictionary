package com.components.database.repository;


import com.components.database.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
