package com.dizhongdi.serviceedu.controller;


import com.dizhongdi.serviceedu.entity.EduTeacher;
import com.dizhongdi.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author dizhongdi
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/serviceedu/teacher")
@Api(description="讲师管理")
public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;

    @GetMapping("/findAll")
    @ApiOperation(value = "所有讲师列表")
    public List<EduTeacher> list(){
        return eduTeacherService.list(null);

    }

    //逻辑删除讲师
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public boolean removeById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        return eduTeacherService.removeById(id);
    }

}

