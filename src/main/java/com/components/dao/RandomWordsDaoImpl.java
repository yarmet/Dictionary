package com.components.dao;

import com.components.models.User;
import com.components.models.Word;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
class RandomWordsDaoImpl implements RandomWordsDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(readOnly = true)
    public List<Word> getAllRandomWords(int wordCount) {
        Query query = entityManager.createQuery("select o from Word o order by rand()");
        query.setMaxResults(wordCount);
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public List<Word> getAllLastRandomWords(int wordCount) {
        Query query = entityManager.createQuery("select o from Word o WHERE date >=:prevMonth  order by rand()");
        query.setParameter("prevMonth", Utils.getPreviousMonth());
        query.setMaxResults(wordCount);
        return query.getResultList();
    }

    @Override
    public List<Word> getRandomWordsForUser(User user, int wordCount) {
        Query query = entityManager.createQuery("select o from Word o WHERE userId=:userId order by rand()");
        query.setParameter("prevMonth", Utils.getPreviousMonth());
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
