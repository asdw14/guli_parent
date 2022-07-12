package com.dizhongdi.servicesta;

import com.dizhongdi.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName:UcenterClient
 * Package:com.dizhongdi.servicesta
 * Description:
 *
 * @Date: 2022/7/12 21:28
 * @Author:dizhongdi
 */
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping(value = "/ucenterservice/apimember/countregister/{day}")
    public Integer registerCount( @PathVariable String day);
}
