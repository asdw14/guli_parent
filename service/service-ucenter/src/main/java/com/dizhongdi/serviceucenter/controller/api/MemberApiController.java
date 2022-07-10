package com.dizhongdi.serviceucenter.controller.api;

import com.dizhongdi.commonutils.JwtUtils;
import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceucenter.entity.LoginInfo;
import com.dizhongdi.serviceucenter.entity.LoginVo;
import com.dizhongdi.serviceucenter.entity.RegisterVo;
import com.dizhongdi.serviceucenter.entity.UcenterMember;
import com.dizhongdi.serviceucenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        LoginInfo loginInfo = memberService.getLoginInfo(id);
        return R.ok().data("item",loginInfo);
    }

    //根据id获取用户信息，返回用户信息对象
    @PostMapping("getInfoUc/{id}")
    public com.dizhongdi.commonutils.vo.UcenterMember getInfo(@PathVariable String id) {
        UcenterMember ucenterMember = memberService.getById(id);
        com.dizhongdi.commonutils.vo.UcenterMember member = new com.dizhongdi.commonutils.vo.UcenterMember();
        BeanUtils.copyProperties(ucenterMember,member);
        return member;
    }
}
