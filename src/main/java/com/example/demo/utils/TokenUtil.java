package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.MyUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: Token工具类
 * @author: scott
 * @date: 2021-05-28 10:06
 */
public class TokenUtil {

    private static String secret = UUID.randomUUID().toString();

    private static Long expiration = 86400L;

    /** 创建token */
    public static String createToken(MyUser myUser){
        Map<String,Object> claims = new HashMap<>(JSONObject.parseObject(JSONObject
                                                            .toJSONString(myUser),Map.class));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }


    /**
     *  解析token
     * */
    public static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**判断token是否过期*/
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }
}
