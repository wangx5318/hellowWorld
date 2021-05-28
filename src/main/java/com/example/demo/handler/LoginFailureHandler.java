package com.example.demo.handler;

import cn.hutool.json.JSONUtil;
import com.example.demo.entity.ResultVo;
import com.example.demo.enum2.HttpStatusEnum;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-26 16:15
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(ResultVo.fail()));
        httpServletResponse.sendError(HttpStatusEnum.INTERNAL_SERVER_ERROR.getCode(),HttpStatusEnum.INTERNAL_SERVER_ERROR.getMessage());
//        httpServletResponse.sendRedirect("/login");
    }
}
