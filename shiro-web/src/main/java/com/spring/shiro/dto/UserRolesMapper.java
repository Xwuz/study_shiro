package com.spring.shiro.dto;

import com.spring.shiro.entity.User;
import com.spring.shiro.entity.UserRoles;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author tiandao
 * @date 2018/9/4 10:56
 */
public interface UserRolesMapper {


    Set<String> getRoleByUserName(@Param("username") String username);

}
