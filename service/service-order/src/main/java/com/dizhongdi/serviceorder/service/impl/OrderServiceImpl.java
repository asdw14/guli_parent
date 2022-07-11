package com.dizhongdi.serviceorder.service.impl;

import com.dizhongdi.serviceorder.entity.Order;
import com.dizhongdi.serviceorder.mapper.OrderMapper;
import com.dizhongdi.serviceorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
