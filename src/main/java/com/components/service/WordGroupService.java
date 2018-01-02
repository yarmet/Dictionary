package com.components.service;

import com.components.database.models.User;
import com.components.database.models.WordGroup;
import com.components.database.repository.JpaUserRepository;
import com.components.database.repository.JpaWordGroupsRepository;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WordGroupService {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private JpaWordGroupsRepository jpaWordGroupsRepository;


    public WordGroup save(WordGroup wordGroup) {
        return jpaWordGroupsRepository.save(wordGroup);
    }


    public WordGroup update(WordGroup wordGroup) {
        return jpaWordGroupsRepository.save(wordGroup);
    }


    public void delete(WordGroup wordGroup) {
        jpaWordGroupsRepository.delete(wordGroup);
    }

    @Transactional
    public List<WordGroup> getAllByCurrentUser() {
        String username = Utils.getCurrentUsername();
        User user = jpaUserRepository.findByUsername(username);
        return jpaWordGroupsRepository.findByUser(user);
    }


}
