package com.components.database.repository;

import com.components.database.models.User;
import com.components.database.models.Word;
import java.util.List;

public interface RandomWordsRepository {

    List<Word> getAnyRandomWords(int wordCount);

    List<Word> getLastRandomWords(int wordCount);

    List<Word> getAnyRandomWordsForUser(User user, int wordCount);

    List<Word> getLastRandomWordsForUser(User user, int wordCount);

}
