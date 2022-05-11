package com.dizhongdi.serviceedu.controller;

import com.dizhongdi.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:EduTeacherLogin
 * Package:com.dizhongdi.serviceedu.controller
 * Description:
 *
 * @Date: 2022/5/11 0:12
 * @Author:dizhongdi
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class EduTeacherLogin {
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("name","admin").data("roles","[admin]");
    }
}
