package com.dizhongdi.serviceedu.service;

import com.dizhongdi.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.serviceedu.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-19
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
