package com.dizhongdi.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.servicebase.exceptionhandler.GuliException;
import com.dizhongdi.serviceedu.entity.*;
import com.dizhongdi.serviceedu.entity.front.CourseQueryVo;
import com.dizhongdi.serviceedu.entity.front.CourseWebVo;
import com.dizhongdi.serviceedu.mapper.EduCourseMapper;
import com.dizhongdi.serviceedu.service.EduChapterService;
import com.dizhongdi.serviceedu.service.EduCourseDescriptionService;
import com.dizhongdi.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.serviceedu.service.EduVideoService;
import com.dizhongdi.serviceedu.vo.course.CourseInfoVo;
import com.dizhongdi.serviceedu.vo.course.CoursePublishVo;
import com.dizhongdi.serviceedu.vo.course.CourseQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    EduVideoService eduVideoService;


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

    //分页查询课程列表
    @Override
    public IPage<EduCourse> pageQuery(Page<EduCourse> coursePage, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQuery.getTitle())){
            wrapper.eq("title",courseQuery.getTitle());
        }
        if (!StringUtils.isEmpty(courseQuery.getTeacherId())){
            wrapper.eq("teacher_id",courseQuery.getTeacherId());
        }
        if (!StringUtils.isEmpty(courseQuery.getSubjectId())){
            wrapper.eq("subject_id",courseQuery.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())){
            wrapper.eq("subject_parent_id",courseQuery.getSubjectParentId());
        }

        return baseMapper.selectPage(coursePage,wrapper);
    }

    //删除课程
    @Override
    public boolean removeCourseById(String id) {
        //删除视频
        eduVideoService.removeByCourseId(id);

        //删除小节
        eduVideoService.removeByCourseId(id);

        //删除章节
        eduChapterService.removeByCourseId(id);
        //删除课程介绍
        eduCourseDescriptionService.removeById(id);
        //删除课程信息
        return this.removeById(id);
    }

    //根据讲师id查询这个讲师的课程列表
    @Override
    public List<EduCourse> selectByTeacherId(String id) {
        List<EduCourse> courseList = baseMapper.selectList(new QueryWrapper<EduCourse>().eq("teacher_id", id));
        return courseList;
    }

    //条件查询课程信息
    @Override
    public Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", courseQuery.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(courseQuery.getSubjectId())) {
            wrapper.eq("subject_id", courseQuery.getSubjectId());
        }

        if (!StringUtils.isEmpty(courseQuery.getBuyCountSort())) {
            wrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseQuery.getPriceSort())) {
            wrapper.orderByDesc("price");
        }

        if (!StringUtils.isEmpty(courseQuery.getTitle())){
            wrapper.eq("title",courseQuery.getTitle());
        }

        baseMapper.selectPage(pageParam,wrapper);
        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public CourseWebVo selectInfoWebById(String courseId) {
        this.updatePageViewCount(courseId);
        return baseMapper.selectInfoWebById(courseId);
    }

    @Override
    public void updatePageViewCount(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);
        baseMapper.updateById(eduCourse.setViewCount(eduCourse.getViewCount()+1));
    }
}
