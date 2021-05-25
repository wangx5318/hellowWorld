package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BuniessException;
import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangx
 * @since 2021-05-23
 */
public interface IUserService extends IService<User> {

    void addUser(User user) throws BuniessException;

    List<User> selectUser(User user);
}
