package com.spring.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * 自定义shirofilter
 *
 * 授权相关：------>继承AuthorizationFilter
 * 认证相关：------>继承AuthenticationFilter
 *
 * @author tiandao
 * @date 2018/9/4 15:10
 */
public class RolesOrFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        // 1.获取主体
        Subject subject = getSubject(servletRequest,servletResponse);
        // Object o ----->过滤器配置中括号里面的role
        String[] roles = (String[]) o;
        if (roles == null || roles.length < 1){
            return true;
        }
        // 满足一个role即可访问
        for (String role : roles){
            if (subject.hasRole(role)){
                return true;
            }
        }
        return false;
    }
}
