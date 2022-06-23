package com.dizhongdi.serviceedu.controller;


import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.entity.EduVideo;
import com.dizhongdi.serviceedu.service.EduVideoService;
import com.dizhongdi.serviceedu.vo.video.VideoInfoForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-19
 */
@Api(description="课时管理")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Resource(type = EduVideoService.class)
    EduVideoService eduVideoService;

    @ApiOperation(value = "新增课时")
    @PostMapping("saveVideoInfo")
    public R save(@ApiParam(name = "videoInfoForm", value = "课时对象", required = true)
                      @RequestBody VideoInfoForm videoInfoForm){
        eduVideoService.saveVideoInfo(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("updateVideoInfo")
    public R updateVideoInfoById(@ApiParam(name = "videoInfoForm", value = "课时对象", required = true)
                  @RequestBody VideoInfoForm videoInfoForm ){
        eduVideoService.updateVideoInfoById(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "获取课时基本信息")
    @GetMapping("videoInfo/{id}")
    public R getVideInfoById(@PathVariable String id ){
        VideoInfoForm videoInfoForm = eduVideoService.getVideoInfoFormById(id);
        return R.ok().data("item",videoInfoForm);
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{id}")
    public R removeById(@PathVariable String id ){
        if(eduVideoService.removeById(id)){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }


}

