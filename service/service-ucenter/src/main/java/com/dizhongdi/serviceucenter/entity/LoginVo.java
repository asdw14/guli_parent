package com.dizhongdi.serviceucenter.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName:LoginVo
 * Package:com.dizhongdi.serviceucenter.entity
 * Description:
 *
 * @Date: 2022/7/8 0:22
 * @Author:dizhongdi
 */
@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginVo {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}