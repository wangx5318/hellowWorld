package com.example.demo.controller;


import com.example.demo.entity.City;
import com.example.demo.service.ICityService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangx
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    @ApiModelProperty("查找城市")
    @GetMapping("/findCity")
    public List<City> findCity(){
        return cityService.findCity();
    }

    @ApiModelProperty("redis测试")
    @GetMapping("/redisTest")
    public String redisTest(){
        Jedis jedis = new Jedis("localhost",6379);
        return jedis.get("a");
    }

}
