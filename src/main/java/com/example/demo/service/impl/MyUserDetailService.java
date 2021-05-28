package com.example.demo.service.impl;

import com.example.demo.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-26 15:41
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //判断账号
        if(!Objects.equals(username,"wx")){
            throw new UsernameNotFoundException("账号密码错误");
        }

        // 权限集合
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(){
            {
                add(new SimpleGrantedAuthority("admin"));
                add(new SimpleGrantedAuthority("finance"));
            }
        };

        System.out.println("#####myUserDetaiService--->loadUserByUsername");

        //返回User对象
        return new MyUser().setUsername(username)
                .setPassword(bCryptPasswordEncoder.encode("123"))
                .setAuthorities(grantedAuthorities);
    }
}
