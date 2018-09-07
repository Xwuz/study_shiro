package com.spring.shiro.controller;

import com.spring.shiro.entity.User;
import com.spring.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author tiandao
 * @date 2018/9/4 9:28
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/subLogin",method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(User user){
        System.out.println(userService.getUserByUserName(user.getUsername()).getPassword());
        // 获取security主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            token.setRememberMe(user.isRememberMe());
            subject.login(token);
        }catch (AuthenticationException e){
            return e.getMessage();
        }
        if (subject.hasRole("admin") || subject.hasRole("user")){
            return "有admin权限";
        }
        return "登陆成功";
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addUser(User user){
        System.out.println(user+"+++++++");
        userService.addUser(user);
        return "添加成功";
    }


    // 1.注解授权   2.shiro过滤器，看配置
//    @RequiresRoles("admin")
    @RequestMapping("/testRole")
    @ResponseBody
    public String testRole(){
        return "testRole success";
    }

//    @RequiresPermissions({"user:delete","user:select"})
    @RequestMapping("/testRole1")
    @ResponseBody
    public String testRole1(){
        return "testRole1 success";
    }

    @RequestMapping("/testPerms")
    @ResponseBody
    public String testPerms(){
        return "testPerms success";
    }

    @RequestMapping("/testPerms1")
    @ResponseBody
    public String testPerms1(){
        return "testPerms1 success";
    }
}
