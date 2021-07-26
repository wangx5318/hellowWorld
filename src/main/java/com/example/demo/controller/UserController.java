package com.example.demo.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BuniessException;
import com.example.demo.entity.City;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangx
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户操作接口")
public class UserController {

    @Autowired
    IUserService userService;

    @ApiOperation(value = "新增用户",notes = "无注意事项",httpMethod = "POST")
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        String msg = "新增成功";
        try {
            userService.addUser(user);
        }catch (BuniessException e){
            msg = e.getMessage();
        }
        return msg;
    }

    @ApiOperation(value = "查询用户",notes = "无注意事项",httpMethod = "POST")
    @PostMapping("/selectUser")
    public List<User> selectUser(@RequestBody User user){
        return userService.selectUser(user);
    }

    @ApiOperation(value = "导出人员信息",notes = "无注意事项",httpMethod = "POST")
    @PostMapping("/exportUser")
    public void exportUser(HttpServletResponse response){
        userService.exportUser(response);
    }

    @ApiOperation(value = "导入人员信息",notes = "无注意事项",httpMethod = "POST")
    @PostMapping("/importExcel")
    public String importExcel(@RequestParam("file") MultipartFile file) {
        try {
            userService.importExcel(file);
            return "导入成功";
        }catch (BuniessException e){
            return e.getMessage();
        }
    }
}
