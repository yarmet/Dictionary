package com.components.service;

import com.components.dao.UserDao;
import com.components.models.Role;
import com.components.models.User;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;


class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // пытаемся найти пользоватяеля
        User user = userDao.findByUsername(username);
        // если такого нет, то кидаем ошибку
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        // обновляем дату последнего логина на сайт
        user.setDateOfLastEntry(Utils.getCurrentTimestampAsUTC());
        userDao.save(user);
        // загружаем все роли пользователя
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(1);
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
