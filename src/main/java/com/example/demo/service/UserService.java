package com.example.demo.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.BuniessException;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.ExcelUtil;
import com.example.demo.utils.FileUtil;
import com.example.demo.utils.WorldUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangx
 * @since 2021-05-23
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User>{

    public void addUser(User user) throws BuniessException {
        if(StringUtils.isEmpty(user.getName())){
            throw new BuniessException("姓名为空");
        }
        this.save(user);
    }

    public List<User> selectUser(@RequestBody User user) {
        QueryWrapper<User> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(user.getName())){
            query.like("name", user.getName());
        }
        return baseMapper.selectList(query);
    }

    public void exportUser(HttpServletResponse response) {
        QueryWrapper<User> query = new QueryWrapper<>();
        List<User> list = baseMapper.selectList(query);
        ExcelUtil.exportExcel(list,"通讯录","技术部",User.class,"通讯录.xls",response);
    }

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
            userList.forEach( e->{
                if("陈佳".equals(e.getName())){
                    throw new BuniessException("鸽子王");
                }
            });
        } catch (IOException e) {
            throw new BuniessException(e.getMessage());
        } catch (Exception e1) {
            throw new BuniessException(e1.getMessage());
        }
    }

    public void generateArchives(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> params = new HashMap<>();
        params.put("name","章狗");
        params.put("sex","男");
        params.put("nickName","吃屎狗");
        WorldUtil.exportWord("world/archives.docx","D:/test","aaa.docx",params,request,response);
    }

    public void importArchives(MultipartFile file) throws Exception{
        FileInputStream fis = new FileInputStream(FileUtil.multipartFileToFile(file));
        XWPFDocument xdoc = new XWPFDocument(fis);
        List<XWPFParagraph> paras = xdoc.getParagraphs();
        for(XWPFParagraph p : paras)//遍历段落
        {
            //获取段落的级别
            String jibie = p.getStyleID();
            p.getParagraphText();
            if(jibie!=null) {
                if(jibie.equals("8")) {
                    System.out.println(p.getParagraphText());
                }else if(jibie.equals("9")) {
                    System.out.println(p.getParagraphText());
                }else if(jibie.equals("10")){       //判断的段落为标题
                    System.out.println(p.getParagraphText());
                }else {      //判断段落为正文
                    System.out.println(p.getParagraphText());
                }
            }
        }
        fis.close();

    }
}
