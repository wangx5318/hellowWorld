package com.example.demo.controller;


import com.example.demo.entity.City;
import com.example.demo.service.CityService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    private CityService cityService;

    @ApiModelProperty("查找城市")
    @GetMapping("/findCity")
    public List<City> findCity(){
        return  cityService.findCity();
    }

}
