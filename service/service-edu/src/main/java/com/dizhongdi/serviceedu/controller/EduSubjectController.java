package com.dizhongdi.serviceedu.controller;


import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.service.EduSubjectService;
import com.dizhongdi.serviceedu.service.EduTeacherService;
import com.dizhongdi.serviceedu.vo.SubjectOneVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
@Api(description="课程列表")
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

//    嵌套课程数据列表
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("")
    public R nestedList(){
        return R.ok().data("items",subjectService.nestedList());
    }



}

