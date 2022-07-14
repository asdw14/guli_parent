package com.dizhongdi.servicesta.controller;


import com.dizhongdi.commonutils.R;
import com.dizhongdi.servicesta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-12
 */
@RestController
@RequestMapping("/admin/servicesta")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    StatisticsDailyService dailyService;

    //在统计表中创建某天的统计数据
    @PostMapping("day/{day}")
    public R createStatisticsByDate(@PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }

    @GetMapping("showChart/{begin}/{end}/{type}")
    public R showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return R.ok().data(map);
    }
}

