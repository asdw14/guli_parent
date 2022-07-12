package com.dizhongdi.serviceedu.client;

import org.springframework.stereotype.Component;

/**
 * ClassName:OrderClientImpl
 * Package:com.dizhongdi.serviceedu.client
 * Description:
 *
 * @Date: 2022/7/12 13:53
 * @Author:dizhongdi
 */
@Component
public class OrderClientImpl implements OrderClient{
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}
