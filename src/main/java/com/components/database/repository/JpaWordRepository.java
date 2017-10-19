package com.components.database.repository;

import com.components.database.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWordRepository extends JpaRepository<Word, Integer> {

}
