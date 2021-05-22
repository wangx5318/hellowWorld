package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
public interface ICityService extends IService<City> {

    IPage<City> findCity(City city);
}
