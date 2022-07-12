package com.dizhongdi.serviceorder.service;

import com.dizhongdi.serviceorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-11
 */
public interface OrderService extends IService<Order> {

    //根据课程id和用户id创建订单，返回订单id
    String saveOrder(String courseId, String memberIdByJwtToken);

}
