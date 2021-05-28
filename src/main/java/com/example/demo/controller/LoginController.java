package com.example.demo.controller;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.MyUser;
import com.example.demo.entity.ResultVo;
import com.example.demo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-27 15:56
 */
@Controller
public class LoginController {

    @Resource
    private AuthenticationManager authenticationManager;

    @RequestMapping("/")
    public String index(){
        return "a/index";
    }

    @RequestMapping("/index")
    public String login(){
        return "a/index";
    }

    @RequestMapping("/home")
    @ResponseBody
    public ResultVo home(){
        return ResultVo.success();
    }

    @RequestMapping("/loginn")
    @ResponseBody
    public ResultVo loginn(@RequestBody @Valid MyUser myUser){
        Authentication authentication = null;
        try
            {
                // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
                authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(myUser.getUsername(), myUser.getPassword()));
            }
            catch (Exception e)
            {
                throw new RuntimeException(e.getMessage());
            }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = TokenUtil.createToken(myUser);
        return ResultVo.success(token);
    }



}
