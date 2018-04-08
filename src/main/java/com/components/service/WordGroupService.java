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

    @Transactional
    public WordGroup save(WordGroup wordGroup) {
        wordGroup.setCreationDate(Utils.getCurrentTimestampAsUTC());
        User user = jpaUserRepository.findByUsername(Utils.getCurrentUsername());
        user.getWordGroups().add(wordGroup);
        wordGroup.setUser(user);
        return jpaWordGroupsRepository.save(wordGroup);
    }


    public WordGroup update(WordGroup wordGroup) {
        return jpaWordGroupsRepository.save(wordGroup);
    }


    public void delete(WordGroup wordGroup) {
        jpaWordGroupsRepository.delete(wordGroup);
    }

    @Transactional
    public List<WordGroup> getGroupsByCurrentUser() {
        String username =  Utils.getCurrentUsername();
        return  jpaWordGroupsRepository.findByUserUsername(username);
    }


}
