package com.example.demo.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.MyUser;
import com.example.demo.enum2.TopicConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消费者
 * */
@Component
public class KafkaConsumer {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.mail.username}")
    private String emailfrom;

    private static final String COUNTNUM = "countnum";

    @KafkaListener(topics = {TopicConstants.EMAIL})
    public void topicEmail(String data){
        MyUser user = JSONObject.parseObject(data , MyUser.class);
        SimpleMailMessage msg = new SimpleMailMessage();
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        msg.setFrom(emailfrom);
        msg.setTo(user.getEmail());
        msg.setSubject("***注册激活信息***");
        Long countNum = redisUtil.increment(COUNTNUM,1);
        msg.setText("<html><body>恭喜你这个逼，你是第"+countNum+"个用户！验证码为："+lineCaptcha.getCode()+"</body></html>");
        msg.setSentDate(new Date());
        javaMailSender.send(msg);
    }
}
