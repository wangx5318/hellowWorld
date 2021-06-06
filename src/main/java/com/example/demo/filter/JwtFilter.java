package com.example.demo.filter;

import com.example.demo.service.impl.MyUserDetailService;
import com.example.demo.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description: token过滤器
 * @author: scott
 * @date: 2021-05-28 10:48
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String auth = httpServletRequest.getHeader("Authorization");
        if(!Objects.isNull(auth)){
            Claims claims = TokenUtil.getTokenBody(auth);
            String username = claims.get("username",String.class);
            if(!Objects.isNull(username) && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
                if(Objects.nonNull(userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
}
