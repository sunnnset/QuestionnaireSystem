package com.xy.mapper;

import com.xy.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserByID(Integer id);

    User getUserByName(String userName);

    // 返回新增加User的UserID
    int addUser(User user);

    int deleteUser(Integer id);

    int updateUser(User user);
}
