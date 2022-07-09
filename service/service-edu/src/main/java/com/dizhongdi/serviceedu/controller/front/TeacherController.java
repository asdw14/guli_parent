package com.dizhongdi.serviceedu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.entity.EduCourse;
import com.dizhongdi.serviceedu.entity.EduTeacher;
import com.dizhongdi.serviceedu.service.EduCourseService;
import com.dizhongdi.serviceedu.service.EduTeacherService;
import com.dizhongdi.serviceedu.vo.course.CourseInfoVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName:TeacherController
 * Package:com.dizhongdi.serviceedu.controller.front
 * Description:
 *
 * @Date: 2022/7/9 21:26
 * @Author:dizhongdi
 */
@CrossOrigin
@RestController
@RequestMapping("eduservice/teacherfront")
public class TeacherController {

    @Autowired
    EduTeacherService eduTeacherService;

    @Autowired
    EduCourseService eduCourseService;

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        Map<String, Object> map = eduTeacherService.pageListWeb(pageParam);
        return R.ok().data(map);
    }

    @ApiOperation(value = "根据id查询讲师，根据讲师id查询课程")
    @GetMapping("{id}")
    public R addCourseInfo( @ApiParam(name = "id", value = "课程ID", required = true) @PathVariable  String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        List<EduCourse> courseList = eduCourseService.selectByTeacherId(id);
        return R.ok().data("teacher",eduTeacher).data("courseList",courseList);
    }
}
