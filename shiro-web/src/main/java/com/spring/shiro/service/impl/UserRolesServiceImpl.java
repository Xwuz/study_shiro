package com.spring.shiro.service.impl;

import com.spring.shiro.dto.UserRolesMapper;
import com.spring.shiro.entity.UserRoles;
import com.spring.shiro.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @author tiandao
 * @date 2018/9/4 13:49
 */
public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    UserRolesMapper rolesMapper;

    @Override
    public Set<String> getRolesByUserName(String username) {
        return rolesMapper.getRoleByUserName(username);
    }
}
