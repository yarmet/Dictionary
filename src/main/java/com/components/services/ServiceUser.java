package com.components.services;

import com.components.tables.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ruslan on 29.11.16.
 */
@Service
@Transactional
public class ServiceUser implements UserDetailsService {

    @Autowired
    private SessionFactory sesionFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = sesionFactory.getCurrentSession().createQuery("from User u where  u.username=:username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }

}
