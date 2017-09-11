package com.components.dao;


import com.components.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
