package com.dizhongdi.serviceorder.service;

import com.dizhongdi.serviceorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-12
 */
public interface PayLogService extends IService<PayLog> {

    Map createNative(String orderNo);

    void updateOrderStatus(Map<String, String> map);

    Map<String, String> queryPayStatus(String orderNo);
}
