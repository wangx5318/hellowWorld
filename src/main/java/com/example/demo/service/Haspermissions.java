package com.example.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-26 16:21
 */
@Service("ss")
public class Haspermissions {

    public Boolean hasPermissions(String permission){

        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities();
        for (GrantedAuthority authority : grantedAuthorities) {
            if(Objects.equals(authority.getAuthority(),permission)) return true;
        }
        return false;
    }

}
