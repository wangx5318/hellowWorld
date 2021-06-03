package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * */
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String topic,Object data){
        kafkaTemplate.send(topic, JSON.toJSONString(data));
    }
}
