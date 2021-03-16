package com.xy.controller;

import com.xy.exceptionHandle.MessageResponse;
import com.xy.exceptionHandle.MsgEnum;
import com.xy.exceptionHandle.ServerException;
import com.xy.pojo.User;
import com.xy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public User getUserByID(int id){
        User user = userService.getUserByID(id);
        if (user == null) {throw new ServerException(MsgEnum.ITEM_NOT_FOUND); }
        return user;
    }

    // 用户注册接口是/auth/register，下面的接口已经废弃
    /*
    @PostMapping("/add")
    public Map<String, Object> addUser(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        try {
            userService.addUser(user);
            map.put("status", 200);
            map.put("msg", "success");
            return map;
        } catch (Exception e){
            e.printStackTrace();
            map.put("status", 500);
            map.put("msg", "fail");
            return map;
        }
    }*/

    @PostMapping("/update")
    public MessageResponse updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new MessageResponse(MsgEnum.SUCCESS);
    }

    // @PostMapping("/delete")
    // 删除用户造成的连锁反应较大，且暴露该接口似乎并无必要。
    public MessageResponse deleteUserByID(int id){
        userService.deleteUserByID(id);
        return new MessageResponse(MsgEnum.SUCCESS);
    }
}
