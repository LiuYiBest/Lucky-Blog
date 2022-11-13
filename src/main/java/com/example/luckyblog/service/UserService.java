package com.example.luckyblog.service;

import com.example.luckyblog.entity.User;
import com.example.luckyblog.mapper.UserMapper;

import javax.inject.Inject;

/**
 * @author liuyidiao
 */
public class UserService {
    private UserMapper userMapper;

    @Inject
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserById(Integer id){
//        return new User(id,"");
        return userMapper.findUserById(id);
    }
}
