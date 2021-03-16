package com.xy.auth.tokenUtil;

import com.xy.auth.pojo.UserToken;
import com.xy.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class TokenGenerator {
    private static final String salt = "298374*(@#&$*()hjklhasva#";
    private int expriyPeriod = 2;       // 单位：小时

    public int getExpriyPeriod() {
        return expriyPeriod;
    }

    public void setExpriyPeriod(int expriyPeriod) {
        this.expriyPeriod = expriyPeriod;
    }

    private int longestExpiryPeriod = 12;       // 单位：小时

    public int getLongestExpiryPeriod() {
        return longestExpiryPeriod;
    }

    public void setLongestExpiryPeriod(int longestExpiryPeriod) {
        this.longestExpiryPeriod = longestExpiryPeriod;
    }

    public String generateTokenWithUserID(int userID){
        String param = LocalDateTime.now().toString() + '/' + userID;
        param += '/' + salt;
        String token = DigestUtils.md5DigestAsHex(param.getBytes(StandardCharsets.UTF_8));
        return token;
    }
}
