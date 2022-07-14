package com.dizhongdi.servicesta.service;

import com.dizhongdi.servicesta.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-12
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    //在统计表中创建某天的统计数据
    void createStatisticsByDay(String day);

    Map<String, Object> getChartData(String begin, String end, String type);
}
