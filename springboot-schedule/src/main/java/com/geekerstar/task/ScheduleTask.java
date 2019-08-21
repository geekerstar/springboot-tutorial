package com.geekerstar.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author geekerstar
 * date: 2019-08-21 09:23
 * description:
 */
@Component
public class ScheduleTask {
    private int count = 0;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "*/6 * * * * ?")
    private void process(){
        System.out.println("task is running"+(count++));
    }

    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime(){
        System.out.println("现在时间是："+dateFormat.format(new Date()));

    }
}
