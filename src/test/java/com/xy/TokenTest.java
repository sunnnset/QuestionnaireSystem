package com.xy;

import com.xy.auth.mapper.UserTokenMapper;
import com.xy.auth.pojo.UserToken;
import com.xy.auth.service.UserTokenService;
import com.xy.auth.tokenUtil.TokenGenerator;
import com.xy.mapper.UserMapper;
import com.xy.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenMapper tokenMapper;

    @Autowired
    private UserTokenService tokenService;

    @Test
    public void tokenTest(){
        UserToken userToken = new UserToken(1, null, null, null);
        tokenMapper.addUserToken(userToken);
    }

    @Test
    public void updateToken(){
        UserToken userToken = tokenMapper.getUserTokenByUserID(1);
        userToken.setToken("12425jkhsdfgkjsdfg");
        tokenMapper.updateUserToken(userToken);
    }

    @Test
    public void deleteToken(){
        tokenMapper.deleteUserToken(1);
    }

    @Test
    public void tokenServiceTest(){
        System.out.println("hello");
        UserToken userToken = tokenService.getUserTokenByToken("70677dcf8387c6ef64e47712ec5237d5");
        System.out.println(userToken);
    }


}
