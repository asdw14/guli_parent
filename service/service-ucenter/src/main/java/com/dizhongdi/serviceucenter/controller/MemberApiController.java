package com.dizhongdi.serviceucenter.controller;

import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceucenter.entity.LoginVo;
import com.dizhongdi.serviceucenter.entity.RegisterVo;
import com.dizhongdi.serviceucenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:MemberApiController
 * Package:com.dizhongdi.serviceucenter.controller.api
 * Description:
 *
 * @Date: 2022/7/8 0:23
 * @Author:dizhongdi
 */
@RestController
@RequestMapping("/ucenterservice/apimember")
@CrossOrigin
public class MemberApiController {
    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
}
