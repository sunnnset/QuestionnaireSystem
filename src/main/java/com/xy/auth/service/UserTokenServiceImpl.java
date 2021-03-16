package com.xy.auth.service;


import com.xy.auth.mapper.UserTokenMapper;
import com.xy.auth.pojo.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenServiceImpl implements UserTokenService {
    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public UserToken getUserTokenByUserID(int userID) {
        return userTokenMapper.getUserTokenByUserID(userID);
    }

    @Override
    public UserToken getUserTokenByToken(String token) {
        return userTokenMapper.getUserTokenByToken(token);
    }

    @Override
    public int addUserToken(UserToken userToken) {
        userTokenMapper.addUserToken(userToken);
        return 0;
    }

    @Override
    public int updateUserToken(UserToken userToken) {
        userTokenMapper.updateUserToken(userToken);
        return 0;
    }

    @Override
    public int deleteUserToken(int userID) {
        userTokenMapper.deleteUserToken(userID);
        return 0;
    }
}
