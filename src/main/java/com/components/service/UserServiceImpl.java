package com.components.service;

import com.components.dao.RoleDao;
import com.components.dao.UserDao;
import com.components.models.Role;
import com.components.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import com.components.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
class UserServiceImpl implements UserService {

    private static final Long ROLE_USER = 1L;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registryUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(Utils.getCurrentTimestampAsUTC());
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(ROLE_USER));
        user.setRoles(roles);
        userDao.save(user);
    }


    @Override
    public User findByUserName(String userName) {
        return userDao.findByUsername(userName);
    }
}
