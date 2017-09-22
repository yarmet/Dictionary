package com.components.service;

import com.components.dao.RandomWordsDao;
import com.components.dao.WordDao;
import com.components.models.Word;
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

    @Override
    public Word save(Word word) {
        word.setDate(new Date());
        return wordDao.save(word);
    }

    @Override
    public Word update(Word word) {
        Date date = wordDao.findOne(word.getId()).getDate();
        word.setDate(date);
        return wordDao.save(word);
    }

    @Override
    public void delete(Word word) {
        wordDao.delete(word);
    }

    @Override
    public List<Word> getRandomWords() {
        return randomWordsDao.getRandomWords(wordCount);
    }

    @Override
    public List<Word> getLastRandomWords() {
        return randomWordsDao.getLastRandomWords(wordCount);
    }
}
