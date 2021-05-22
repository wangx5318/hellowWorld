package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.City;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangx
 * @since 2021-04-12
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Override
    public IPage<City> findCity(City city) {
        QueryWrapper<City> query = new QueryWrapper<>();
        query.eq("CountryCode", city.getCountryCode());
        Page<City> page = new Page<>();
        IPage<City> iPage = baseMapper.selectPage(page, query);
        return iPage;
    }
}
