package com.dizhongdi.serviceedu.service.impl;

import com.dizhongdi.servicebase.exceptionhandler.GuliException;
import com.dizhongdi.serviceedu.entity.EduCourse;
import com.dizhongdi.serviceedu.entity.EduCourseDescription;
import com.dizhongdi.serviceedu.mapper.EduCourseMapper;
import com.dizhongdi.serviceedu.service.EduCourseDescriptionService;
import com.dizhongdi.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.serviceedu.vo.CourseInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
}
