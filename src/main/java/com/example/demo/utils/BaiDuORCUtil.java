package com.example.demo.utils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 百度ORC
 * */
@Component
public class BaiDuORCUtil {

    @Value("${baidu.appid}")
    private String appid;
    @Value("${baidu.key}")
    private String key;
    @Value("${baidu.secret}")
    private String secret;

    //识别身份证
    public JSONObject idCard(String filePath,String face){
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "false");
        options.put("detect_risk", "false");
        AipOcr aipOcr = new AipOcr(appid,key,secret);
        JSONObject json = aipOcr.idcard(filePath,face,options);
        return json;
    }
}
