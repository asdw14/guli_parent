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
    String uploadVideo(MultipartFile file);
}
