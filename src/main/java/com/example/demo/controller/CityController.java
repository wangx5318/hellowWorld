package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.City;
import com.example.demo.service.CityService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import redis.clients.jedis.Jedis;

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
@Api(tags = "城市接口")
public class CityController {

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "查找城市",notes = "注意事项",httpMethod = "POST", response = City.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "countryCode", value = "国家代码", required = true)
//    }
//    )
    @PostMapping("/findCity")
    public IPage<City> findCity(@RequestBody City city){
        return cityService.findCity(city);
    }

    @ApiOperation("redis测试")
    @GetMapping("/redisTest")
    public String redisTest(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("b", "阿斯顿");
        return jedis.get("b");
    }

}
