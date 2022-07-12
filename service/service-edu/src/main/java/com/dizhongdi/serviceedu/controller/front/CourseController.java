package com.dizhongdi.serviceedu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.commonutils.JwtUtils;
import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.client.OrderClient;
import com.dizhongdi.serviceedu.entity.EduCourse;
import com.dizhongdi.serviceedu.entity.front.CourseQueryVo;
import com.dizhongdi.serviceedu.entity.front.CourseWebVo;
import com.dizhongdi.serviceedu.service.EduChapterService;
import com.dizhongdi.serviceedu.service.EduCourseService;
import com.dizhongdi.serviceedu.vo.chapter.ChapterVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    OrderClient orderClient;

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

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public R getById(@PathVariable String courseId, HttpServletRequest request){
        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);
        //查询当前课程的章节信息
        List<ChapterVo> chapterVos = eduChapterService.nestedList(courseId);
        //根据用户id和课程id查询是否购买
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        boolean buyCourse = orderClient.isBuyCourse(memberId, courseId);

        return R.ok().data("course",courseWebVo).data("chapterVoList",chapterVos).data("isbuy",buyCourse);
    }
}
