package com.example.demo.utils;

import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-26 17:44
 */
@Component
public class RedisUtil {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public String getStringValByKey(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setStringValByKey(String key,String val){
         stringRedisTemplate.opsForValue().set(key,val);
    }

    public void delStringVal(String key){
        stringRedisTemplate.delete(key);
    }

}
