package com.components.dao;

import com.components.models.Word;
import java.util.List;

public interface RandomWordsDao {

    List<Word> getRandomWords();

    List<Word> getLastRandomWords();

}
