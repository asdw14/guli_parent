package com.dizhongdi.serviceedu.controller;


import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.entity.EduChapter;
import com.dizhongdi.serviceedu.service.EduChapterService;
import com.dizhongdi.serviceedu.vo.chapter.ChapterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@CrossOrigin
@Api(description="课程章节管理")
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Resource(type = EduChapterService.class)
    EduChapterService eduChapterService;

    @GetMapping("chapterList")
    @ApiOperation(value = "嵌套章节数据列表")
    public R nestedListByCourseId(String courseId){
        List<ChapterVo> chapterList = eduChapterService.nestedList(courseId);
        return R.ok().data("items",chapterList);
    }


}

