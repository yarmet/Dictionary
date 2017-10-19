package com.components.database.repository;

import com.components.database.models.User;
import com.components.database.models.Word;
import com.components.utils.Utils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
class RandomWordsRepositoryImpl implements CustomWordrepository {


    @PersistenceContext
    private EntityManager entityManager;


    public List<Word> getAnyRandomWords(int wordCount) {
        Query query = entityManager.createQuery("select o from Word o order by rand()");
        query.setMaxResults(wordCount);
        return query.getResultList();
    }


    public List<Word> getLastRandomWords(int wordCount) {
        Query query = entityManager.createQuery("select o from Word o WHERE createDate >=:prevMonth  order by rand()");
        query.setParameter("prevMonth", Utils.getPreviousMonth());
        query.setMaxResults(wordCount);
        return query.getResultList();
    }


    @Override
    public List<Word> getAnyRandomWordsForUser(User user, int wordCount) {
        Query query = entityManager.createQuery("select w from User u join u.words w WHERE u.id =:userId order by rand()");
        query.setParameter("userId", user.getId());
        query.setMaxResults(wordCount);
        return query.getResultList();
    }


    @Override
    public List<Word> getLastRandomWordsForUser(User user, int wordCount) {
        Query query = entityManager.createQuery("select w from User u join u.words w WHERE u.id =:userId AND w.createDate >=:prevMonth order by rand()");
        query.setParameter("prevMonth", Utils.getPreviousMonth());
        query.setParameter("userId", user.getId());
        query.setMaxResults(wordCount);
        return query.getResultList();
    }


    public int updateWord(Word word) {
        Query query = entityManager.createQuery("update Word w set w.russian = :rus, w.english = :eng where w.id = :id");
        query.setParameter("rus", word.getRussian());
        query.setParameter("eng", word.getEnglish());
        query.setParameter("id", word.getId());
        return query.executeUpdate();
    }

}
