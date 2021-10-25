package com.example.demo.timeUtil;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskUtils {
    // 添加定时任务，每天上午十点运行
//    @Scheduled(cron = "0 12 15 * * ?")
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void doTask() {
//        System.out.println("我是定时任务~");
//    }
}