package com.dizhongdi.serviceedu.client;

import com.dizhongdi.commonutils.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName:VodClient
 * Package:com.dizhongdi.serviceedu.client
 * Description:
 *
 * @Date: 2022/6/28 23:25
 * @Author:dizhongdi
 */
@FeignClient("service-vod")
@Component
public interface VodClient {
    @DeleteMapping("deleteVideo/{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId);
}
