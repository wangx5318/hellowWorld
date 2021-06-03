package com.example.demo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.example.demo.entity.MyUser;
import com.example.demo.entity.ResultVo;
import com.example.demo.enum2.HttpStatusEnum;
import com.example.demo.enum2.TopicConstants;
import com.example.demo.utils.KafkaSender;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-27 15:56
 */
@Controller
public class LoginController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    KafkaSender kafkaSender;

    private static final String prefix = "a/";


    @RequestMapping("/")
    public String index(){
        return this.login();
    }

    @RequestMapping("/index")
    public String login(){
        return prefix + "sign-in";
    }

    @GetMapping("home")
    public String home(){
        return prefix +"home";
    }

    @RequestMapping("/loginn")
    @ResponseBody
    public ResultVo loginn(@RequestBody MyUser myUser){
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

    @GetMapping("/signup")
    public String signup(){
        return prefix + "sign-up";
    }

    @PostMapping("/creatUser")
    @ResponseBody
    public ResultVo creatUser(@Validated @RequestBody MyUser user){
        try {
            //将数据添加到数据库
            // .....

            //消息队列发送邮件
            kafkaSender.send(TopicConstants.EMAIL,user);
            return ResultVo.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVo.fail(HttpStatusEnum.INTERNAL_SERVER_ERROR.getCode(),e.getMessage());
        }

    }



}
