package com.components.service;

import com.components.database.repository.CustomWordrepository;
import com.components.database.repository.JpaUserRepository;
import com.components.database.repository.JpaWordRepository;
import com.components.database.models.User;
import com.components.database.models.Word;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service
class WordsServiceImpl implements WordsService {


    @Value("${words.count}")
    private int wordCount;


    @Autowired
    private JpaWordRepository jpaWordRepository;


    @Autowired
    private CustomWordrepository customWordrepository;


    @Autowired
    private JpaUserRepository jpaUserRepository;


    @Override
    @Transactional
    public Word save(Word word) {
        User user = getLoggedUser();
        word.setCreateDate(Utils.getCurrentTimestampAsUTC());
        word.setUser(user);
        user.setWords(Collections.singletonList(word));
        return jpaWordRepository.save(word);
    }


    @Override
    @Transactional
    public Word update(Word word) {
        int updatedRowCount = customWordrepository.updateWord(word);
        return updatedRowCount == 1 ? word : null;
    }


    @Override
    public void delete(Word word) {
        jpaWordRepository.delete(word);
    }


    @Override
    public List<Word> getRandomWords() {
        return Utils.userIsLogged() ?
                customWordrepository.getAnyRandomWordsForUser(getLoggedUser(), wordCount) :
                customWordrepository.getAnyRandomWords(wordCount);
    }


    @Override
    public List<Word> getLastRandomWords() {
        return Utils.userIsLogged() ?
                customWordrepository.getLastRandomWordsForUser(getLoggedUser(), wordCount) :
                customWordrepository.getLastRandomWords(wordCount);
    }


    private User getLoggedUser() {
        String username = Utils.getCurrentUsername();
        return jpaUserRepository.findByUsername(username);
    }

}
