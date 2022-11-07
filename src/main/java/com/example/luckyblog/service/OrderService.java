package com.example.luckyblog.service;

import javax.inject.Inject;

/**
 * @author liuyidiao
 */
public class OrderService {

    private UserService userService;


//    通过构造器注入
    @Inject
    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public  void placeOrder(Integer userId, String item){
        userService.getUserById(userId);
    }
}
