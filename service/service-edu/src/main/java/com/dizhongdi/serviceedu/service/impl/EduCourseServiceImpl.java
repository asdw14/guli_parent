package com.dizhongdi.serviceedu.service.impl;

import com.dizhongdi.servicebase.exceptionhandler.GuliException;
import com.dizhongdi.serviceedu.entity.EduCourse;
import com.dizhongdi.serviceedu.entity.EduCourseDescription;
import com.dizhongdi.serviceedu.mapper.EduCourseMapper;
import com.dizhongdi.serviceedu.service.EduCourseDescriptionService;
import com.dizhongdi.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.serviceedu.vo.CourseInfoVo;
import com.dizhongdi.serviceedu.vo.CoursePublishVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-19
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //注入课程简介接口
    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    //添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert==0){
            throw new GuliException(200001,"课程插入失败");
        }
        //再添加后获取课程id
        String cid = eduCourse.getId();
        eduCourseDescriptionService.save(new EduCourseDescription().setDescription(courseInfoVo.getDescription()).setId(cid));

        return cid;
    }

    //根据ID查询课程
    @Override
    public CourseInfoVo getCourseInfoFormById(String id) {

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        //获取课程基本信息
        EduCourse eduCourse = baseMapper.selectById(id);
        if(eduCourse == null){
            throw new GuliException(20001, "数据不存在");
        }
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        //获取课程简介
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(eduCourse.getId());
        //给课程对象里添加课程简介并返回
        if(eduCourse != null || courseDescription != null) {
            courseInfoVo.setDescription(courseDescription.getDescription());
        }
        return courseInfoVo;
    }

    @Override
    public String updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i==0){
            throw new GuliException(200001,"课程更新失败");
        }
        //再添加后获取课程id
        eduCourseDescriptionService.updateById(new EduCourseDescription().setId(courseInfoVo.getId()).setDescription(courseInfoVo.getDescription()));

        return courseInfoVo.getId();
    }

//    根据ID获取课程发布信息
    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    //根据id发布课程
    @Override
    public void publishCourseById(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);
        eduCourse.setStatus("Normal");
        baseMapper.updateById(eduCourse);
    }
}
