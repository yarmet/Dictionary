package com.components.dao;

import com.components.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleDao extends JpaRepository<Role, Long> {
}
