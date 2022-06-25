package com.dizhongdi.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.entity.EduCourse;
import com.dizhongdi.serviceedu.service.EduCourseService;
import com.dizhongdi.serviceedu.vo.course.CourseInfoVo;
import com.dizhongdi.serviceedu.vo.course.CoursePublishVo;
import com.dizhongdi.serviceedu.vo.course.CourseQuery;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-19
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    EduCourseService eduCourseService;
    @ApiOperation(value = "添加课程")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("cid",id);

    }
    @ApiOperation(value = "更新课程")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfoById(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok().data("cid",id);
    }


    @ApiOperation(value = "数据回显根据ID查询课程")
    @GetMapping("courseInfo/{id}")
    public R addCourseInfo( @ApiParam(name = "id", value = "课程ID", required = true) @PathVariable  String id){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfoFormById(id);
        return R.ok().data("item",courseInfoVo);
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("coursePublishInfo/{id}")
    public R getCoursePublishVoById(@PathVariable String id){
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishVoById(id);
        return R.ok().data("item",coursePublishVo);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publishCourse/{id}")
    public R publishCourseById(@PathVariable String id){
        eduCourseService.publishCourseById(id);
        return R.ok();
    }

    @ApiOperation(value = "课程列表")
    @GetMapping("listCourse")
    public R listCourse(){
        List<EduCourse> list = eduCourseService.list(new QueryWrapper<>());
        return R.ok().data("items" , list);
    }

    @ApiOperation(value = "分页课程列表")
    @PostMapping("getPageList/{page}/{limit}")
    public R pageQuery(@PathVariable Long page, @PathVariable Long limit , @RequestBody CourseQuery courseQuery){
        Page<EduCourse> coursePage = new Page<>(page,limit);
        IPage<EduCourse> courseIPage = eduCourseService.pageQuery(coursePage,courseQuery);
        return R.ok().data("items" , courseIPage.getRecords()).data("total",courseIPage.getTotal());
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("removeById/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        boolean result = eduCourseService.removeCourseById(id);
        if (result){
            return R.ok();
        }else {
            return R.error().message("删除失败,请重试");
        }
    }

}

