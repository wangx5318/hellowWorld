package com.example.demo.service.impl;

import com.example.demo.entity.City;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cdd
 * @since 2021-04-12
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

}
