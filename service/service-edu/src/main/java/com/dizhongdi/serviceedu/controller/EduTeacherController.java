package com.dizhongdi.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceedu.entity.EduTeacher;
import com.dizhongdi.serviceedu.service.EduTeacherService;
import com.dizhongdi.serviceedu.vo.TeacherQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    public R list(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);

    }

    //逻辑删除讲师
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if (b){
            return R.ok();

        }else {
            return R.error();
        }
    }

    //分页查询
    @PostMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询")
    public R pageListTeacher(@PathVariable long current , @PathVariable long limit){
        IPage<EduTeacher> page = eduTeacherService.page(new Page<>(current, limit),null);

        return R.ok().data("total",page.getTotal()).data("rows",page.getRecords());

    }
    //条件查询带分页
    @PostMapping("pageTeacherQuery/{current}/{limit}")
    @ApiOperation(value = "条件查询带分页")
    public R pageTeacherQuery(@PathVariable long current , @PathVariable long limit , @RequestBody TeacherQuery teacherQuery){
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }
        IPage<EduTeacher> page = eduTeacherService.page(new Page<>(current, limit), wrapper);

        return R.ok().data("total",page.getTotal()).data("rows",page.getRecords());

    }

    @PostMapping("addTeacher")
    @ApiOperation(value = "新增讲师")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("{id}")
    @ApiOperation(value = "通过id查讲师")
    public R queryById(@PathVariable String id){
        return R.ok().data("items",eduTeacherService.getById(id));
    }

    @PutMapping("")
    @ApiOperation(value = "修改讲师")
    public R updateById(EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

