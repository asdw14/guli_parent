package com.dizhongdi.serviceedu.controller;


import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.service.EduSubjectService;
import com.dizhongdi.serviceedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-15
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        subjectService.importSubjectData(file,subjectService);
        //判断返回集合是否为空
        return R.ok();
    }



}

