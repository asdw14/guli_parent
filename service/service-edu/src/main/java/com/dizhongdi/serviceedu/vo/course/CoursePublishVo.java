package com.dizhongdi.serviceedu.vo.course;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName:CoursePublishVo
 * Package:com.dizhongdi.serviceedu.vo
 * Description:
 *
 * @Date: 2022/6/24 0:06
 * @Author:dizhongdi
 */
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}