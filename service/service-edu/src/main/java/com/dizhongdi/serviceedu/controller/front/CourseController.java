package com.dizhongdi.serviceedu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.entity.EduCourse;
import com.dizhongdi.serviceedu.entity.front.CourseQueryVo;
import com.dizhongdi.serviceedu.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ClassName:CourseController
 * Package:com.dizhongdi.serviceedu.controller.front
 * Description:
 *
 * @Date: 2022/7/10 16:59
 * @Author:dizhongdi
 */
@CrossOrigin
@RestController
@RequestMapping("eduservice/coursefront")
public class CourseController {

    @Autowired
    EduCourseService courseService;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery){
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String, Object> map = courseService.pageListWeb(pageParam, courseQuery);
        return  R.ok().data(map);
    }
}
