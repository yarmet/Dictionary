package com.components.database.repository;

import com.components.database.models.Word;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RandomWordsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Word> getAnyRandomWords(long groupId, int wordCount) {
        return entityManager.createQuery("select w from Word w join w.wordGroup wg where wg.id=:groupId order by rand()", Word.class)
                .setParameter("groupId", groupId)
                .setMaxResults(wordCount)
                .getResultList();

    }

}
