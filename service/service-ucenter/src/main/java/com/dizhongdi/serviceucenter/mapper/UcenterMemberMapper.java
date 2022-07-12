package com.dizhongdi.serviceucenter.mapper;

import com.dizhongdi.serviceucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-08
 */
@Mapper
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    //某一天的注册人数
    Integer selectRegisterCount(String day);
}
