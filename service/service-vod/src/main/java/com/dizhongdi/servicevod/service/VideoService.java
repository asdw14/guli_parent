package com.dizhongdi.servicevod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:VideoService
 * Package:com.dizhongdi.servicevod.service
 * Description:
 *
 * @Date: 2022/6/27 0:23
 * @Author:dizhongdi
 */
public interface VideoService {
    //上传视频到阿里云视频点播
    String uploadVideo(MultipartFile file);

    //删除云端视频
    void removeVideo(String id);
}
