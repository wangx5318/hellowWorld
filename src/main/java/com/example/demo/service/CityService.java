package com.example.demo.service;

import com.example.demo.entity.City;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangx
 * @since 2021-04-12
 */
public interface CityService extends IService<City> {

    List<City> findCity();
}
