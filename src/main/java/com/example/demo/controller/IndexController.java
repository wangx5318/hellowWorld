package com.example.demo.controller;

import com.example.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-26 15:32
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    RedisUtil redisUtil;

    public void setValToRedis(){
        redisUtil.setStringValByKey("wx","王玄 !");
    }

    public String getValToRedis(){
        return redisUtil.getStringValByKey("wx");
    }

    @GetMapping("/wx")
    public String wx(){
        setValToRedis();
        return "hello wx sb ！";
    }

    @GetMapping("/sb")
    @PreAuthorize("@ss.hasPermissions('admin')")
    public String sb(){
        String val = getValToRedis();
        if(Objects.isNull(val)){
            setValToRedis();
        }
        return "hello sb = " + getValToRedis();
    }

    @GetMapping("/engineer")
    @PreAuthorize("@ss.hasPermissions('engineer')")
    public String login(){
        return "hello engineer!";
    }
}
