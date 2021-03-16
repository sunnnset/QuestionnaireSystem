package com.xy.auth.mapper;

import com.xy.auth.pojo.UserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenMapper {

    public UserToken getUserTokenByUserID(int userID);

    public UserToken getUserTokenByToken(String token);

    public int addUserToken(UserToken userToken);

    public int updateUserToken(UserToken userToken);

    public int deleteUserToken(int userID);
}
