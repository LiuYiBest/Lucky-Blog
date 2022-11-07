package com.example.luckyblog.service;

/**
 * @author liuyidiao
 */
public class UserService {

    public User getUserById(Integer id){
        return new User(id,"");
    }
}
