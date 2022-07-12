package com.dizhongdi.serviceedu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName:OrderClient
 * Package:com.dizhongdi.serviceedu.client
 * Description:
 *
 * @Date: 2022/7/12 13:53
 * @Author:dizhongdi
 */
@Component
@FeignClient(value = "service-order", fallback = OrderClientImpl.class)
public interface OrderClient {
    //查询订单信息
    @GetMapping("/orderservice/order/isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable("memberid") String memberid, @PathVariable("id") String id);
}