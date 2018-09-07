package com.spring.shiro.service.impl;

import com.spring.shiro.dto.UserMapper;
import com.spring.shiro.entity.User;
import com.spring.shiro.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tiandao
 * @date 2018/9/4 11:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.getPassWordByUserName(username);
    }

    @Override
    public void addUser(User user) {
        //生成盐（部分，需要存入数据库中）
//        String random=new SecureRandomNumberGenerator().nextBytes().toHex();
        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
        String result = new Md5Hash(user.getPassword(),
                user.getUsername(),1).toString();
        user.setPassword(result);
        userMapper.addUsers(user);
    }
}
