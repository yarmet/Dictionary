package com.components.database.repository;

import com.components.database.models.Word;
import com.components.database.models.WordGroup;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class RandomWordsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Word> getAnyRandomWords(WordGroup wordGroup, int wordCount) {
        Query query = entityManager.createQuery("select w from Word w where w.wordGroup=:wordGroup  order by rand()");
        query.setParameter("wordGroup", wordGroup);
        query.setMaxResults(wordCount);
        return query.getResultList();
    }

}
