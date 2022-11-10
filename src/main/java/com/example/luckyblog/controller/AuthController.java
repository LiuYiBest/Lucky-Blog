package com.example.luckyblog.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Map;

@Controller
public class AuthController {
    private UserDetailsService userDetailsService;

    @Inject
    public AuthController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/auth")
    @ResponseBody
    public Object auth(ModelMap map){
        return new Result();
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public void login(@RequestBody Map<String,Object> usernameAndpasswordJson){
        System.out.println(usernameAndpasswordJson);
//        this.userService = userService;
//        this.authenticationManager = authenticationManager;
//        this.authService = authService;
    }

    private  static class Result{
        public String getStatus(){
            return "OK";
        }
        public Boolean getIsLogin(){
            return false;
        }
    }

}
