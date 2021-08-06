package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.City;
import com.example.demo.mapper.CityMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangx
 * @since 2021-04-12
 */
@Service
public class CityService extends ServiceImpl<CityMapper, City>  {

    public IPage<City> findCity(City city) {
        QueryWrapper<City> query = new QueryWrapper<>();
        query.eq("CountryCode", city.getCountryCode());
        Page<City> page = new Page<>();
        IPage<City> iPage = baseMapper.selectPage(page, query);
        return iPage;
    }
}
