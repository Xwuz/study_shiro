package com.spring.shiro.service;

import com.spring.shiro.entity.User;
import com.spring.shiro.entity.UserRoles;

import java.util.List;
import java.util.Set;

/**
 * @author tiandao
 * @date 2018/9/4 11:06
 */
public interface UserRolesService {


    Set<String> getRolesByUserName(String username);

}
