package com.dizhongdi.serviceedu.mapper;

import com.dizhongdi.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dizhongdi.serviceedu.entity.front.CourseWebVo;
import com.dizhongdi.serviceedu.vo.course.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-19
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo selectCoursePublishVoById(String id);
//    关联查询课程和讲师信息
    CourseWebVo selectInfoWebById(String courseId);
}
