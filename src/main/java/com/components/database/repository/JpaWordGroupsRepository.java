package com.components.database.repository;

import com.components.database.models.User;
import com.components.database.models.WordGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaWordGroupsRepository extends JpaRepository<WordGroup, Long> {

    List<WordGroup> findByUser(User user);

}
