package com.dizhongdi.serviceedu.controller;


import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.entity.EduChapter;
import com.dizhongdi.serviceedu.service.EduChapterService;
import com.dizhongdi.serviceedu.vo.chapter.ChapterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "新增章节")
    @PostMapping("")
    public R save( @RequestBody EduChapter chapter){
        boolean save = eduChapterService.save(chapter);
        if (save){
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("{id}")
    public R getById ( @PathVariable String id){
        EduChapter chapter = eduChapterService.getById(id);
            return R.ok().data("item",chapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PutMapping("{id}")
    public R upadteById ( @PathVariable String id , @RequestBody EduChapter chapter){
        boolean b = eduChapterService.updateById(chapter.setId(id));
        if (b){
            return R.ok().data("item",chapter);

        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("{id}")
    public R upadteById ( @PathVariable String id){
        boolean b = eduChapterService.removeChapterById(id);
        if (b){
            return R.ok();

        }else {
            return R.error();
        }
    }


}

