package com.components.dao;

import com.components.models.Word;
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

    @Value("${words.count}")
    private int wordCount;

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(readOnly = true)
    public List<Word> getRandomWords() {
        Query query = entityManager.createQuery("select o from Word o order by rand()");
        query.setMaxResults(wordCount);
        return query.getResultList();
    }


    @Transactional(readOnly = true)
    public List<Word> getLastRandomWords() {
        Query query = entityManager.createQuery("select o from Word o WHERE date >=:prevMonth  order by rand()");
        query.setParameter("prevMonth", getPreviousMonth());
        query.setMaxResults(wordCount);
        return query.getResultList();
    }


    private static Date getPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

}
