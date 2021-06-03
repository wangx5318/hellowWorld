package com.example.demo.controller;

import com.example.demo.entity.ResultVo;
import com.example.demo.enum2.HttpStatusEnum;
import com.example.demo.utils.BaiDuORCUtil;
import com.google.common.collect.Maps;
import org.apache.kafka.common.protocol.types.Field;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${baidu.front}")
    private String front;

    @Autowired
    BaiDuORCUtil baiDuORCUtil;

    @PostMapping("/getIdCardData")
    public ResultVo getIdCardData(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        ResultVo vo = this.uploadFile(request,file);
        String filePath = vo.getData().toString();
        JSONObject jsonObject = baiDuORCUtil.idCard(filePath,front);
        Map<String,String> data = getIdCardInfo(jsonObject);
        if(Objects.isNull(data.get("id"))){
            return ResultVo.fail(HttpStatusEnum.INTERNAL_SERVER_ERROR.getCode(),"请上传正确的身份证正面照！");
        }
        data.put("filePath",filePath);
        vo.setData(data);
        return vo;
    }

    @PostMapping("/upload")
    public ResultVo upload(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        return this.uploadFile(request,file);

    }

    public ResultVo uploadFile(HttpServletRequest request,MultipartFile file){
        try {
            String destFileName = request.getServletContext().getRealPath("") + "uploaded" + File.separator + file.getOriginalFilename();
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile);
            return ResultVo.success(destFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResultVo.fail(HttpStatusEnum.INTERNAL_SERVER_ERROR.getCode(),"上传失败！");
        } catch (IOException e) {
            e.printStackTrace();
            return ResultVo.fail(HttpStatusEnum.INTERNAL_SERVER_ERROR.getCode(),"上传失败！");
        }
    }

    public Map<String,String> getIdCardInfo(JSONObject jsonObject){
        JSONObject words = (JSONObject)jsonObject.get("words_result");
        Map<String,String> map = Maps.newHashMap();
        Iterator it = words.keySet().iterator();
        while (it.hasNext()){
            String key = it.next().toString();
            JSONObject obj = (JSONObject)words.get(key);
            map.put(changeKey(key),obj.get("words").toString());
        }
        return map;
    }

    public String changeKey(String key){
        String name = "";
        switch (key){
            case "姓名" : name = "name"; break;
            case "民族" : name = "national";break;
            case "住址" : name = "address";break;
            case "公民身份号码" : name = "id";break;
            case "出生" : name = "birthday";break;
            case "性别" : name = "sex";break;
        }
        return name;
    }

}
