package com.components.database.repository;

import com.components.database.models.User;
import com.components.database.models.Word;
import java.util.List;

public interface RandomWordsRepository {

    List<Word> getAllRandomWords(int wordCount);

    List<Word> getAllLastRandomWords(int wordCount);

    List<Word> getRandomWordsForUser(User user, int wordCount);

    List<Word> getLastRandomWordsForUser(User user, int wordCount);

}
