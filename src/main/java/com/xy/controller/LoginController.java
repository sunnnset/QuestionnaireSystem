package com.xy.controller;

import com.xy.auth.pojo.UserToken;
import com.xy.auth.service.UserTokenService;
import com.xy.auth.tokenUtil.TokenGenerator;
import com.xy.exceptionHandle.MessageResponse;
import com.xy.exceptionHandle.MsgEnum;
import com.xy.pojo.User;
import com.xy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private TokenGenerator tokenGenerator;

    @RequestMapping("/checkUserName")
    public MessageResponse checkUserNameExist(String userName){
        User user = userService.getUserByUserName(userName);
        if (user == null) { return new MessageResponse(MsgEnum.REGISTER_SUCCESS); }
        else { return  new MessageResponse(MsgEnum.USER_NAME_EXIST); }
    }

    @RequestMapping("/register")
    public MessageResponse register(@RequestBody Map<String, String> requestMap) {

        String registerUserName = requestMap.get("userName");
        String registerPassword = requestMap.get("password");
        System.out.println("username:"+registerUserName);
        System.out.println("password:"+registerPassword);
        if (registerPassword == null || registerUserName == null) {
            return new MessageResponse(MsgEnum.INVALID_PARAM);
        }
        User user = new User(null, registerUserName, registerPassword, "user");
        int newUserID = userService.addUser(user);
        if (newUserID == -1){ return new MessageResponse(MsgEnum.USER_NAME_EXIST); }
        return new MessageResponse(MsgEnum.SUCCESS);
    }

    // 登录认证
    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> requestMap, HttpServletResponse response){
        HashMap<String, Object> map = new HashMap<>();
        String requestUserName = requestMap.get("userName");
        String requestPassword = requestMap.get("password");
        System.out.println("requestUserName:"+requestUserName);
        System.out.println("requestPassword: "+requestPassword);
        if (requestPassword == null || requestUserName == null){
            map.put("status", 444);
            map.put("msg", "INVALID INPUT");
            return map;
        }
        User user = userService.getUserByUserName(requestUserName);
        System.out.println("user: "+user);
        System.out.println(requestPassword.equals(user.getPassword()));
        if (user == null || ! requestPassword.equals(user.getPassword())){
            // 用户名不存在或者用户名、密码不匹配
            map.put("status", 444);
            map.put("msg", "MISMATCH");
            return map;
        } else{ // 用户名密码正确
            map.put("userID", user.getUserID());
            map.put("userName", user.getUserName());
            map.put("role", user.getRole());
            System.out.println("user: "+user);
            UserToken userToken = userTokenService.getUserTokenByUserID(user.getUserID());
            if (userToken == null){
                map.put("status", 444);
                map.put("msg", "TOKEN NOT FOUND");
                return map;
            }
            String newToken = tokenGenerator.generateTokenWithUserID(user.getUserID());
            System.out.println("userToken: "+userToken);
            System.out.println("newToken: "+newToken);
            userToken.setToken(newToken);
            // 记录token的创建时间和过期时间
            userToken.setCreateTime(LocalDateTime.now());
            userToken.setExpireTime(LocalDateTime.now().plusHours(tokenGenerator.getExpriyPeriod()));
            userTokenService.updateUserToken(userToken);

            response.setHeader("token", newToken);
            return map;
        }
    }


    @RequestMapping("/logout")
    public MessageResponse logout(HttpServletRequest request){
        String token = request.getHeader("token");
        System.out.println(token);
        UserToken userToken = userTokenService.getUserTokenByToken(token);
        if (userToken == null){ return new MessageResponse(MsgEnum.TOKEN_INVALID); }
        userToken.setToken(null);
        userToken.setCreateTime(null);
        userToken.setExpireTime(null);
        userTokenService.updateUserToken(userToken);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

}
