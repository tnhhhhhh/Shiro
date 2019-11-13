package com.tnh.shrio.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: TNH
 * @create: 2019/11/13 14:44
 */
@Controller
public class ShiroController {
    @RequestMapping("add")
    public String add(){
        return "add";
    }
    @RequestMapping("update")
    public String update(){
        return "update";
    }
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("login")
    public String login(String username, String password,Model model){
        //获取 Subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行认证方法，失败会抛出异常
        try {
            subject.login(token);
            return "add";
        } catch (UnknownAccountException e) {
            //登陆失败退回登陆页面
            model.addAttribute("msg","用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping("unAuth")
    public String unAuth(){
        return "unAuth";
    }
}
