package com.components.service;

import com.components.database.repository.JpaUserRepository;
import com.components.database.repository.JpaWordRepository;
import com.components.database.models.User;
import com.components.database.models.Word;
import com.components.database.repository.RandomWordsRepositoryImpl;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service
public class WordsService {


    @Value("${words.count}")
    private int wordCount;


    @Autowired
    private JpaWordRepository jpaWordRepository;


    @Autowired
    private RandomWordsRepositoryImpl customWordrepository;


    @Autowired
    private JpaUserRepository jpaUserRepository;



    @Transactional
    public Word save(Word word) {
        User user = getLoggedUser();
        word.setCreateDate(Utils.getCurrentTimestampAsUTC());
        word.setUser(user);
        user.setWords(Collections.singletonList(word));
        return jpaWordRepository.save(word);
    }



    @Transactional
    public Word update(Word word) {
        int updatedRowCount = customWordrepository.updateWord(word);
        return updatedRowCount == 1 ? word : null;
    }



    public void delete(Word word) {
        jpaWordRepository.delete(word);
    }



    public List<Word> getRandomWords() {
        return Utils.userIsLogged() ?
                customWordrepository.getAnyRandomWordsForUser(getLoggedUser(), wordCount) :
                customWordrepository.getAnyRandomWords(wordCount);
    }



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
