package com.example.luckyblog.service;

import com.example.luckyblog.entity.User;
import com.example.luckyblog.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author liuyidiao
 */
@Service
public class UserService implements UserDetailsService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Map<String,String> userPasswords =new ConcurrentHashMap<>() ;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        save("root","root");
    }

//    public UserService(){
//        userPasswords.put("root","root");
//    }

    public UserService(Map<String, String> userPasswords) {
        this.userPasswords = userPasswords;
    }

    public void save(String username, String password){
        userPasswords.put(username,bCryptPasswordEncoder.encode(password));
    }
    public String getPassword(String username){
        return userPasswords.get(username);
    }

    private UserMapper userMapper;

    @Inject
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserById(Integer id){
//        return new User(id,"");
        return userMapper.findUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userPasswords.containsKey(username)){
            throw new UsernameNotFoundException(username+"不存在!");
        }
        String password =userPasswords.get(username);

        return new org.springframework.security.core.userdetails.User(username,password, Collections.emptyList());
    }
}
