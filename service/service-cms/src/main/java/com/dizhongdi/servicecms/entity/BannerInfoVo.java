package com.dizhongdi.servicecms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ClassName:BannerInfoVo
 * Package:com.dizhongdi.servicecms.entity
 * Description:
 *
 * @Date: 2022/7/9 11:34
 * @Author:dizhongdi
 */

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerInfoVo {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "链接地址")
    private String linkUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
