package com.dizhongdi.serviceedu.client;

import com.dizhongdi.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName:VodFileDegradeFeignClient
 * Package:com.dizhongdi.serviceedu.client
 * Description:
 *
 * @Date: 2022/6/30 0:40
 * @Author:dizhongdi
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out，删除失败");
    }

    @Override
    public R removeVideoList(List<String> videoIdList) {
        return R.error().message("time out，删除失败");

    }
}
