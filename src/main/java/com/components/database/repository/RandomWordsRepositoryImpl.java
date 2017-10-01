package com.components.database.repository;

import com.components.database.models.User;
import com.components.database.models.Word;
import com.components.utils.Utils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
class RandomWordsRepositoryImpl implements RandomWordsRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(readOnly = true)
    public List<Word> getAnyRandomWords(int wordCount) {
        Query query = entityManager.createQuery("select o from Word o order by rand()");
        query.setMaxResults(wordCount);
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public List<Word> getLastRandomWords(int wordCount) {
        Query query = entityManager.createQuery("select o from Word o WHERE date >=:prevMonth  order by rand()");
        query.setParameter("prevMonth", Utils.getPreviousMonth());
        query.setMaxResults(wordCount);
        return query.getResultList();
    }

    @Override
    public List<Word> getAnyRandomWordsForUser(User user, int wordCount) {
        Query query = entityManager.createQuery("select o from Word o WHERE userId=:userId order by rand()");
        query.setParameter("userId", user.getId());
        query.setMaxResults(wordCount);
        return query.getResultList();
    }

    @Override
    public List<Word> getLastRandomWordsForUser(User user, int wordCount) {
        Query query = entityManager.createQuery("select o from Word o WHERE userId=:userId AND date >=:prevMonth  order by rand()");
        query.setParameter("prevMonth", Utils.getPreviousMonth());
        query.setParameter("userId", user.getId());
        query.setMaxResults(wordCount);
        return query.getResultList();
    }


}
