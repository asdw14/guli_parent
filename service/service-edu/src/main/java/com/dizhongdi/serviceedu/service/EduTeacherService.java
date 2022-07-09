package com.dizhongdi.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.serviceedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-05-08
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);
}
