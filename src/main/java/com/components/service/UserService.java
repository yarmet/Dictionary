package com.components.service;


import com.components.database.models.User;

public interface UserService {

    void registryUser(User user);

    User findByUserName(String userName);

}
