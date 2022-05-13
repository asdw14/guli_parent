package com.dizhongdi.serviceoss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:FileService
 * Package:com.dizhongdi.serviceoss.service
 * Description:
 *
 * @Date: 2022/5/13 22:16
 * @Author:dizhongdi
 */
public interface FileService {
    String upload(MultipartFile file);
}
