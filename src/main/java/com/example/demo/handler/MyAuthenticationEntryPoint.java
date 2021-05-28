package com.example.demo.handler;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.entity.ResultVo;
import com.example.demo.enum2.HttpStatusEnum;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义认证异常
 * @author: scott
 * @date: 2021-05-27 9:46
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        httpServletResponse.getWriter().write(JSONUtil.
                toJsonStr(ResultVo.fail(HttpStatusEnum.UNAUTHORIZED.getCode(),
                        HttpStatusEnum.UNAUTHORIZED.getMessage())));

        //跳转
        httpServletResponse.sendRedirect("/index");
    }
}
