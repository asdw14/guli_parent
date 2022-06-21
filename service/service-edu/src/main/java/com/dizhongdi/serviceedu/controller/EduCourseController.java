package com.dizhongdi.serviceedu.controller;


import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.service.EduCourseService;
import com.dizhongdi.serviceedu.vo.CourseInfoVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}

