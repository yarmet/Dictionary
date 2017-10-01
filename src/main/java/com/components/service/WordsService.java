package com.components.service;


import com.components.database.models.Word;

import java.util.List;

public interface WordsService {

    Word save(Word word);

    Word update(Word word);

    void delete(Word word);

    List<Word> getRandomWords();

    List<Word> getLastRandomWords();
}
