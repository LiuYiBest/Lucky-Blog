package com.example.luckyblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class AuthController {
    @GetMapping("/auth")
    @ResponseBody
    public Object auth(ModelMap map){
        return new Result();
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public void login(@RequestBody Map<String,Object> usernameAndpasswordJson){
        System.out.println(usernameAndpasswordJson);
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
