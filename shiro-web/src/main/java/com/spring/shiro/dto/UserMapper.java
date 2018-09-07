package com.spring.shiro.dto;

import com.spring.shiro.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tiandao
 * @date 2018/9/4 10:56
 */
public interface UserMapper {

    List<User> getAllUsers();

    User getPassWordByUserName(@Param("username")String username);

    void addUsers(User user);
}
