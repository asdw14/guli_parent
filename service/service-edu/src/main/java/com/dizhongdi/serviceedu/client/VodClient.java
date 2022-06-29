package com.dizhongdi.serviceedu.client;

import com.dizhongdi.commonutils.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName:VodClient
 * Package:com.dizhongdi.serviceedu.client
 * Description:
 *
 * @Date: 2022/6/28 23:25
 * @Author:dizhongdi
 */
@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    //远程调用根据id删除云端视频
    @DeleteMapping("/vodservice/deleteVideo/{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId);

    //远程调用批量删除云端视频
    @DeleteMapping("/vodservice/deletebatch")
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
