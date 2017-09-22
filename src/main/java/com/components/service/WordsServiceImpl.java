package com.components.service;

import com.components.dao.RandomWordsDao;
import com.components.dao.UserDao;
import com.components.dao.WordDao;
import com.components.models.User;
import com.components.models.Word;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
class WordsServiceImpl implements WordsService {

    @Value("${words.count}")
    private int wordCount;

    @Autowired
    private WordDao wordDao;

    @Autowired
    private RandomWordsDao randomWordsDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Word save(Word word) {
        word.setDate(Utils.getCurrentTimestampAsUTC());
        word.setUserId(getLoggedUser().getId());
        return wordDao.save(word);
    }

    @Override
    public Word update(Word word) {
        Date date = wordDao.findOne(word.getId()).getDate();
        word.setDate(date);
        word.setUserId(getLoggedUser().getId());
        return wordDao.save(word);
    }

    @Override
    public void delete(Word word) {
        wordDao.delete(word);
    }

    @Override
    public List<Word> getRandomWords() {
        boolean logged = Utils.userIsLogged();
        if (logged) {
            return randomWordsDao.getRandomWordsForUser(getLoggedUser(), wordCount);
        }
        return randomWordsDao.getAllRandomWords(wordCount);
    }


    @Override
    public List<Word> getLastRandomWords() {
        boolean logged = Utils.userIsLogged();
        if (logged) {
            return randomWordsDao.getLastRandomWordsForUser(getLoggedUser(), wordCount);
        }
        return randomWordsDao.getAllLastRandomWords(wordCount);
    }


    private User getLoggedUser() {
        String username = Utils.getCurrentUsername();
        return userDao.findByUsername(username);
    }

}
