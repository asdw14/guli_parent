package com.dizhongdi.servicesta.scheduled;

import com.dizhongdi.commonutils.DateUtil;
import com.dizhongdi.servicesta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName:ScheduledTask
 * Package:com.dizhongdi.servicesta.scheduled
 * Description:
 *
 * @Date: 2022/7/12 21:44
 * @Author:dizhongdi
 */
@Component
public class ScheduledTask {
    @Autowired
    StatisticsDailyService dailyService;

    /**
     * 测试
     * 每天七点到二十三点每30秒执行一次
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void task1() {
        System.out.println("*********++++++++++++*****执行了");
        String day = DateUtil.formatDate(new Date());
        dailyService.createStatisticsByDay(day);
    }


    /**
     * 每天凌晨1点执行定时
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        //获取上一天的日期
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        dailyService.createStatisticsByDay(day);

    }

}
