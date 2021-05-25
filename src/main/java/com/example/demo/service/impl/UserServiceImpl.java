package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BuniessException;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.template.utility.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangx
 * @since 2021-05-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void addUser(User user) throws BuniessException {
        if(StringUtils.isEmpty(user.getName())){
            throw new BuniessException("姓名为空");
        }
        this.save(user);
    }

    @Override
    public List<User> selectUser(@RequestBody User user) {
        QueryWrapper<User> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(user.getName())){
            query.like("name", user.getName());
        }
        return baseMapper.selectList(query);
    }
}
