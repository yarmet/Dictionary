package com.components.services;

import com.components.tables.User;
import com.components.tables.UserRole;
import com.components.util.Util;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ruslan on 29.11.16.
 */
@Service
@Transactional
public class RegistryService {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean registryUser(User user)  {
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where  u.username=:name");
        query.setParameter("name", user.getUsername());
        User result = (User) query.uniqueResult();

        if (result != null) {  // если пользователь уже есть в базе, то возвращаем false
            return false;
        } else {               // если нет - то регистрируем его
            user.setPassword(Util.encodePassword(user.getPassword()));
            UserRole userRole = (UserRole) sessionFactory.getCurrentSession().load(UserRole.class, 2);

            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(userRole);

            user.setUserRoles(userRoles);
            user.setRegistrationDate(new Date());
            sessionFactory.getCurrentSession().save(user);
            return true;
        }
    }

}
