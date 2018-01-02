package com.components.service;

import com.components.database.models.WordGroup;
import com.components.database.repository.JpaWordRepository;
import com.components.database.models.Word;
import com.components.database.repository.RandomWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WordsService {


    @Value("${words.count}")
    private int wordCount;


    @Autowired
    private JpaWordRepository jpaWordRepository;


    @Autowired
    private RandomWordsRepository randomWordsRepository;


    public Word update(Word word) {
        return jpaWordRepository.save(word);
    }


    public void delete(Word word) {
        jpaWordRepository.delete(word);
    }


    public Word save(Word word) {
        return jpaWordRepository.save(word);
    }


    public List<Word> getRandomWords(WordGroup wordGroup) {
        return randomWordsRepository.getAnyRandomWords(wordGroup, wordCount);
    }

}
