package com.example.demo.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BuniessException;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.utils.ExcelUtil;
import freemarker.template.utility.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Override
    public void exportUser(HttpServletResponse response) {
        QueryWrapper<User> query = new QueryWrapper<>();
        List<User> list = baseMapper.selectList(query);
        ExcelUtil.exportExcel(list,"通讯录","技术部",User.class,"通讯录.xls",response);
    }

    @Override
    public void importExcel(MultipartFile file) {
        ImportParams importParams = new ImportParams();
        // 数据处理
        //表格标题行数,默认0
        importParams.setHeadRows(1);
        //表头行数,默认1
        importParams.setTitleRows(1);
        //是否需要校验上传的Excel,默认false
        importParams.setNeedVerfiy(false);

        try {
            ExcelImportResult<User> result = ExcelImportUtil.importExcelMore(file.getInputStream(), User.class, importParams);
            List<User> userList = result.getList();
            System.out.println(userList);
        } catch (IOException e) {
            throw new BuniessException(e.getMessage());
        } catch (Exception e1) {
            throw new BuniessException(e1.getMessage());
        }
    }
}
