package com.example.luckyblog.controller;

import com.example.luckyblog.entity.User;
import com.example.luckyblog.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.security.Security;
import java.util.Map;

@Controller
public class AuthController {
    private UserDetailsService userDetailsService;
    private UserService userService;

    private AuthenticationManager authenticationManager;


    @Inject
    public AuthController(UserService userService,UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth")
    @ResponseBody
    public Object auth(ModelMap map) {
//

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userName.contains("anonymous")){
            return new Result("Ok", "用户没有登录", false);
        }else{
            return new Result("ok",null,true,userService.getUserByusername(userName));
        }
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public Result login(@RequestBody Map<String, Object> usernameAndpasswordJson) {
        System.out.println(usernameAndpasswordJson);
        String username = usernameAndpasswordJson.get("username").toString();
        String password = usernameAndpasswordJson.get("password").toString();
        UserDetails userDetails =null ;
//        去查找对应用户名的密码去比对
        try {
             userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return new Result("fail", "用户不存在", false);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//       如果登录失败
        try {
            authenticationManager.authenticate(token);
//            用户信息保存

            SecurityContextHolder.getContext().setAuthentication(token);
            User loggedInUser = new User(1, "张三");
            return new Result("ok", "登录成功", true, loggedInUser);
        } catch (BadCredentialsException e) {
            return new Result("fail", "密码不正确", false);
        }

        //        this.userService = userService;
//        this.authenticationManager = authenticationManager;
//        this.authService = authService;
    }

    private static class Result {

        String status;
        String msg;
        boolean isLogin;
        Object data;

        public Result(String status, String msg, boolean isLogin) {
            this(status, msg, isLogin, null);
        }

        public Result(String status, String msg, Boolean isLogin, Object data) {
            this.status = status;
            this.msg = msg;
            this.isLogin = isLogin;
            this.data = data;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isLogin() {
            return isLogin;
        }

        public void setLogin(boolean login) {
            isLogin = login;
        }


    }

}
