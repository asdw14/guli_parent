package com.dizhongdi.servicesta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dizhongdi.servicesta.UcenterClient;
import com.dizhongdi.servicesta.entity.StatisticsDaily;
import com.dizhongdi.servicesta.mapper.StatisticsDailyMapper;
import com.dizhongdi.servicesta.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-12
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    UcenterClient ucenterClient;

    @Override
    public void createStatisticsByDay(String day) {
        //删除这天已存在的统计对象
        baseMapper.delete(new QueryWrapper<StatisticsDaily>().eq("date_calculated",day));

        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        //远程调用获取注册人数
        daily.setRegisterNum(ucenterClient.registerCount(day));
        daily.setLoginNum(RandomUtils.nextInt(100, 200));
        daily.setVideoViewNum(RandomUtils.nextInt(100, 200));
        daily.setCourseNum(RandomUtils.nextInt(100, 200));
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Object> getChartData(String begin, String end, String type) {
        List<StatisticsDaily> dayList = baseMapper.selectList(new QueryWrapper<StatisticsDaily>().select(type, "date_calculated").between("date_calculated",begin,end));
        Map<String, Object> map = new HashMap<>();
        List<Integer> dataList = new ArrayList<Integer>();
        List<String> dateList = new ArrayList<String>();

        for (StatisticsDaily daily: dayList) {
            //添加日期
            dateList.add(daily.getDateCalculated());
            //添加数据
            switch (type) {
                case "register_num":
                    dataList.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    dataList.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    dataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    dataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }

        map.put("dataList", dataList);
        map.put("dateList", dateList);
        return map;
    }
}
