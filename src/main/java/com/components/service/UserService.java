package com.components.service;


import com.components.models.User;

public interface UserService {

    void registryUser(User user);

    User findByUserName(String userName);

}