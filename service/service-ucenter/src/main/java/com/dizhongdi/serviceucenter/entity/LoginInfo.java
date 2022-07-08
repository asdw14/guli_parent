package com.dizhongdi.serviceucenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * ClassName:LoginInfo
 * Package:com.dizhongdi.serviceucenter.entity
 * Description:
 *
 * @Date: 2022/7/8 17:30
 * @Author:dizhongdi
 */
@Data
@Accessors(chain = true)
public class LoginInfo {
    @ApiModelProperty(value = "会员id")
    private String id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别 1 女，2 男")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "用户头像")
    private String avatar;


}
