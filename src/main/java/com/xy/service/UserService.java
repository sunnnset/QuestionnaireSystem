package com.xy.service;

import com.xy.pojo.User;


public interface UserService {

    int addUser(User user);

    User getUserByID(int id);

    User getUserByUserName(String userName);

    void updateUser(User user);

    void deleteUserByID(int id);
}
