package com.components.services;

import com.components.tables.Word;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ruslan on 01.12.16.
 */
@Component
public class WordsService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Word> getRandomWords(int count) {
        return (List<Word>) sessionFactory.getCurrentSession().createQuery("select o from Word o order by rand()").setMaxResults(count).list();
    }

    @Transactional
    public List<Word> getLastRandomWords(int count) {
        Query query = sessionFactory.getCurrentSession().createQuery("select o from Word o WHERE date >=:prevMonth  order by rand()");
        query.setParameter("prevMonth", getPreviousMonth());
        query.setMaxResults(count);
        return (List<Word>) query.list();
    }

    @Transactional
    public void updateWord(Word word) {
        Query query = sessionFactory.getCurrentSession().createQuery("update Word set english = :english, russian = :russian where id = :id  ");
        query.setParameter("id", word.getId());
        query.setParameter("english", word.getEnglish());
        query.setParameter("russian", word.getRussian());
        query.executeUpdate();
    }


    private Date getPreviousMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

}
