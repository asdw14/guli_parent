package com.dizhongdi.serviceorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ClassName:UcenterClient
 * Package:com.dizhongdi.serviceedu.client
 * Description:
 *
 * @Date: 2022/7/10 22:48
 * @Author:dizhongdi
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    @PostMapping("/ucenterservice/apimember/getInfoUc/{id}")
    public com.dizhongdi.commonutils.vo.UcenterMember getInfo(@PathVariable String id);
}
