package com.dizhongdi.servicemsm.controller;

import com.dizhongdi.commonutils.R;
import com.dizhongdi.servicemsm.service.impl.MsmServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * ClassName:MsmApiController
 * Package:com.dizhongdi.servicemsm.controller
 * Description:
 *
 * @Date: 2022/7/7 23:20
 * @Author:dizhongdi
 */
@RestController
@CrossOrigin
@RequestMapping("/api/msm")
@Api(description="验证码发送")
public class MsmApiController {
    @Autowired
    MsmServiceImpl msmService;
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(value = "/send/{phone}")
    public R send(@PathVariable String phone) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        String code = (String) redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
        //如果从redis获取不到，
        // 生成验证码，
        code = msmService.getCode();
        if (msmService.send(phone,"1",code)){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();

        }
        return R.error().message("验证码发送失败，请重新尝试！");
    }

}
