package com.dizhongdi.serviceedu.service;

import com.dizhongdi.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    void importSubjectData(MultipartFile file, EduSubjectService subjectService);
}
