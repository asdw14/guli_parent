package com.dizhongdi.servicesta.controller;


import com.dizhongdi.commonutils.R;
import com.dizhongdi.servicesta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-12
 */
@RestController
@RequestMapping("/servicesta/statistics-daily")
public class StatisticsDailyController {
    @Autowired
    StatisticsDailyService dailyService;

    //在统计表中创建某天的统计数据
    @PostMapping("{day}")
    public R createStatisticsByDate(@PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }
}

