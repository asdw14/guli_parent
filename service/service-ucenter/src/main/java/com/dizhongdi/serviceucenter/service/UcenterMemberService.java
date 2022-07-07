package com.dizhongdi.serviceucenter.service;

import com.dizhongdi.serviceucenter.entity.LoginVo;
import com.dizhongdi.serviceucenter.entity.RegisterVo;
import com.dizhongdi.serviceucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-08
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    //    会员登录
    String login(LoginVo loginVo);
    //    会员注册
    void register(RegisterVo registerVo);
}
