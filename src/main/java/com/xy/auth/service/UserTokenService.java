package com.xy.auth.service;

import com.xy.auth.pojo.UserToken;

public interface UserTokenService {

    public UserToken getUserTokenByUserID(int userID);

    public UserToken getUserTokenByToken(String token);

    public int addUserToken(UserToken userToken);

    public int updateUserToken(UserToken userToken);

    public int deleteUserToken(int userID);

}
