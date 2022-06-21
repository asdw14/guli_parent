package com.dizhongdi.serviceedu.vo.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:ChapterVo
 * Package:com.dizhongdi.serviceedu.vo
 * Description:
 *
 * @Date: 2022/6/21 16:16
 * @Author:dizhongdi
 */
@ApiModel(value = "章节信息")
@Data
public class ChapterVo {
    String id;
    String title;
    List<VideoVo> children = new ArrayList<>();
}
