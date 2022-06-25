package com.dizhongdi.serviceedu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.serviceedu.vo.course.CourseInfoVo;
import com.dizhongdi.serviceedu.vo.course.CoursePublishVo;
import com.dizhongdi.serviceedu.vo.course.CourseQuery;

import java.util.List;

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

//    根据ID查询课程
    CourseInfoVo getCourseInfoFormById(String id);

//    更新课程
    String updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVoById(String id);

    //根据id发布课程
    void publishCourseById(String id);

    //分页查询课程列表
    IPage<EduCourse> pageQuery(Page<EduCourse> coursePage, CourseQuery courseQuery);

    boolean removeCourseById(String id);
}
