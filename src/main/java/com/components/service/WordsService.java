package com.components.service;

import com.components.database.models.WordGroup;
import com.components.database.repository.JpaWordGroupsRepository;
import com.components.database.repository.JpaWordRepository;
import com.components.database.models.Word;
import com.components.database.repository.RandomWordsRepository;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class WordsService {


    @Value("${words.count}")
    private int wordCount;


    @Autowired
    private JpaWordRepository jpaWordRepository;

    @Autowired
    private JpaWordGroupsRepository jpaWordGroupsRepository;

    @Autowired
    private RandomWordsRepository randomWordsRepository;

    @Transactional
    public Word update(Word word) {
        return jpaWordRepository.save(word);
    }

    @Transactional
    public void delete(Word word) {
        jpaWordRepository.delete(word);
    }

    @Transactional
    public Word save(Word word) {
        word.setCreateDate(Utils.getCurrentTimestampAsUTC());
        WordGroup wordGroup = jpaWordGroupsRepository.findOne(word.getWordGroup().getId());
        word.setWordGroup(wordGroup);
        wordGroup.getWords().add(word);
        return jpaWordRepository.save(word);
    }

    @Transactional
    public List<Word> getRandomWords(long groupId) {
        return randomWordsRepository.getAnyRandomWords(groupId, wordCount);
    }

}
