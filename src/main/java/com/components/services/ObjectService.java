package com.components.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


@Service
@Transactional
public class ObjectService {


    @Autowired
    private SessionFactory sessionFactory;


    public void saveObject(Object object) throws SQLException {
        sessionFactory.getCurrentSession().save(object);
    }

    public void deleteObject(Object object) throws SQLException {
        sessionFactory.getCurrentSession().delete(object);
    }

    public void updateObject(Object object) throws SQLException {
        sessionFactory.getCurrentSession().update(object);
    }

    public <T> T getObjectById(Class<T> type, Serializable id) throws SQLException {
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    public <T> List<T> getAllObjects(Class<T> type) throws SQLException {
        return sessionFactory.getCurrentSession().createCriteria(type).list();
    }

}