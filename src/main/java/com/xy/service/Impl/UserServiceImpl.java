package com.xy.service.Impl;

import com.xy.auth.mapper.UserTokenMapper;
import com.xy.auth.pojo.UserToken;
import com.xy.mapper.UserMapper;
import com.xy.pojo.User;
import com.xy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public int addUser(User user) {
        User userByName = userMapper.getUserByName(user.getUserName());
        if (userByName!= null){
            System.out.println("user name exist!");
            return -1;
        }
        userMapper.addUser(user);
        int userID = user.getUserID();
        UserToken userToken = new UserToken(userID, null, null, null);
        System.out.println("userToken: "+userToken);
        userTokenMapper.addUserToken(userToken);
        return userID;
    }

    @Override
    public User getUserByID(int id) {
        return userMapper.getUserByID(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUserByID(int id) {
        userMapper.deleteUser(id);
    }
}
