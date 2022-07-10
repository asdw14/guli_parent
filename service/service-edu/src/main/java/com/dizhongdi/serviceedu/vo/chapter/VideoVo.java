package com.dizhongdi.serviceedu.vo.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * ClassName:VideoVo
 * Package:com.dizhongdi.serviceedu.vo
 * Description:
 *
 * @Date: 2022/6/21 16:16
 * @Author:dizhongdi
 */
@ApiModel(value = "章节下小节信息")
@Data
public class VideoVo {
    String id;
    String title;
    String videoSourceId;
}
