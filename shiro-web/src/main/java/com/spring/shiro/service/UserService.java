package com.spring.shiro.service;

import com.spring.shiro.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tiandao
 * @date 2018/9/4 11:06
 */
public interface UserService {

    List<User> getAllUsers();

    User getUserByUserName(String username);

    void addUser(User user);
}
