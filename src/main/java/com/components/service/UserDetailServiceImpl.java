package com.components.service;

import com.components.database.models.Role;
import com.components.database.repository.JpaUserRepository;
import com.components.database.models.User;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;



class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JpaUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // пытаемся найти пользоватяеля
        User user = userRepository.findByUsername(username);
        // если такого нет, то кидаем ошибку
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        // обновляем дату последнего логина на сайт
        user.setDateOfLastEntry(Utils.getCurrentTimestampAsUTC());
        userRepository.save(user);

        return user;
    }
}
