package com.dizhongdi.servicevod.controller;

import com.dizhongdi.commonutils.R;
import com.dizhongdi.servicevod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:VideoAdminController
 * Package:com.dizhongdi.servicevod
 * Description:
 *
 * @Date: 2022/6/27 0:20
 * @Author:dizhongdi
 */
@RestController
@RequestMapping("/vodservice")
@CrossOrigin
@Api(description="阿里云视频点播微服务")
public class VideoAdminController {

    @Autowired
    VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(MultipartFile file){
        String videoId = videoService.uploadVideo(file);
        return R.ok().data("videoId",videoId).message("视频上传成功");

    }

    @DeleteMapping("deleteVideo/{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true) @PathVariable String videoId){
        videoService.removeVideo(videoId);
        return R.ok().message("删除视频成功");
    }
}
