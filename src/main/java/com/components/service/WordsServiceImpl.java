package com.components.service;

import com.components.database.repository.RandomWordsRepository;
import com.components.database.repository.UserRepository;
import com.components.database.repository.WordRepository;
import com.components.database.models.User;
import com.components.database.models.Word;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
class WordsServiceImpl implements WordsService {

    @Value("${words.count}")
    private int wordCount;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private RandomWordsRepository randomWordsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Word save(Word word) {
        word.setDate(Utils.getCurrentTimestampAsUTC());
        word.setUserId(getLoggedUser().getId());
        return wordRepository.save(word);
    }

    @Override
    @Transactional
    public Word update(Word word) {
        Date date = wordRepository.findOne(word.getId()).getDate();
        word.setDate(date);
        word.setUserId(getLoggedUser().getId());
        return wordRepository.save(word);
    }

    @Override
    public void delete(Word word) {
        wordRepository.delete(word);
    }

    @Override
    public List<Word> getRandomWords() {
        return Utils.userIsLogged() ?
                randomWordsRepository.getAnyRandomWordsForUser(getLoggedUser(), wordCount) :
                randomWordsRepository.getAnyRandomWords(wordCount);
    }


    @Override
    public List<Word> getLastRandomWords() {
        return Utils.userIsLogged() ?
                randomWordsRepository.getLastRandomWordsForUser(getLoggedUser(), wordCount) :
                randomWordsRepository.getLastRandomWords(wordCount);
    }


    private User getLoggedUser() {
        String username = Utils.getCurrentUsername();
        return userRepository.findByUsername(username);
    }

}
