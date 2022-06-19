package com.dizhongdi.serviceedu.service;

import com.dizhongdi.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.serviceedu.vo.SubjectOneVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    //导入课程数据列表
    void importSubjectData(MultipartFile file, EduSubjectService subjectService);
//    嵌套课程数据列表
    List<SubjectOneVo> nestedList();
}
