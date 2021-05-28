package com.example.demo.handler;

import com.example.demo.entity.MyUser;
import com.example.demo.utils.TokenUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-26 16:04
 */
@Component
public class LoginSucessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//        httpServletResponse.getWriter().write("登录成功！！！");

        //创建Token,返回给前端
        System.out.println("ccccc");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        httpServletResponse.addHeader("Authorization", TokenUtil.createToken(new MyUser()));
        httpServletResponse.sendRedirect("/index/sb");
    }
}

