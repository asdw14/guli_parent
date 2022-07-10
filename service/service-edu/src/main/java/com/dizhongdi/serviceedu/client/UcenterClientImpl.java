package com.dizhongdi.serviceedu.client;

import com.dizhongdi.commonutils.vo.UcenterMember;
import org.springframework.stereotype.Component;

/**
 * ClassName:UcenterClientImpl
 * Package:com.dizhongdi.serviceedu.client
 * Description:
 *
 * @Date: 2022/7/10 22:49
 * @Author:dizhongdi
 */
@Component
public class UcenterClientImpl implements UcenterClient{
    @Override
    public UcenterMember getInfo(String id) {
        return new UcenterMember().setId(id);
    }
}
