package com.spring.shiro.dto;

import com.spring.shiro.entity.Permission;
import com.spring.shiro.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author tiandao
 * @date 2018/9/4 10:56
 */
public interface PermissionMapper {


    Set<String> getPermissionByRoleName(@Param("roleName") Set<String> roleName);

}
