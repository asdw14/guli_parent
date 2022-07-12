package com.dizhongdi.serviceucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dizhongdi.commonutils.JwtUtils;
import com.dizhongdi.commonutils.MD5;
import com.dizhongdi.servicebase.exceptionhandler.GuliException;
import com.dizhongdi.serviceucenter.entity.LoginInfo;
import com.dizhongdi.serviceucenter.entity.LoginVo;
import com.dizhongdi.serviceucenter.entity.RegisterVo;
import com.dizhongdi.serviceucenter.entity.UcenterMember;
import com.dizhongdi.serviceucenter.mapper.UcenterMemberMapper;
import com.dizhongdi.serviceucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-08
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) ) {
            throw new GuliException(20001,"error");
        }

        //获取会员
        UcenterMember member = this.getOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (null == member){
            throw new GuliException(20001,"error");

        }
        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())) {
            throw new GuliException(20001,"error");
        }

        //校验是否被禁用
        if(member.getIsDisabled()) {
            throw new GuliException(20001,"error");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;

    }

    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        //校验参数
        if(StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(nickname) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new GuliException(20001,"error");
        }

        //如果手机号已经注册过
        if (baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile",mobile))>0){
            throw new GuliException(20001,"error");
        }

        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode = (String) redisTemplate.opsForValue().get(mobile);
        if(!code.equals(mobleCode)) {
            throw new GuliException(20001,"error");
        }

        //添加注册信息到数据库
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setNickname(nickname);
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar("https://dizhongdi-guli.oss-cn-hangzhou.aliyuncs.com/cover/1.jpg");
        this.save(ucenterMember);

    }

//    根据token获取用户信息
    @Override
    public LoginInfo getLoginInfo(String id) {
        UcenterMember ucenterMember = this.getById(id);
        LoginInfo loginInfo = new LoginInfo();
        if (ucenterMember != null) {
            BeanUtils.copyProperties(ucenterMember, loginInfo);

        }
        return loginInfo;
    }

    @Cacheable(value = "openid", key = "'info'")
    @Override
    public UcenterMember getByOpenid(String openid) {

        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);

        UcenterMember member = baseMapper.selectOne(queryWrapper);
        return member;
    }

    //统计某一天的注册人数
    @Override
    public Integer countRegisterByDay(String day) {
        //根据用户数据添加时间查询记录
        return baseMapper.selectRegisterCount(day);
    }
}
