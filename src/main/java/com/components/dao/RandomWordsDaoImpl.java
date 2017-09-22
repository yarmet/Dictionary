package com.components.dao;

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
    public List<Word> getRandomWords(int wordCount) {
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


}
