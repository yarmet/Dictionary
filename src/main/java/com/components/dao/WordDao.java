package com.components.dao;

import com.components.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordDao extends JpaRepository<Word, Integer> {
}
