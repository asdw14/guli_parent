package com.dizhongdi.serviceoss.controller;

import com.dizhongdi.commonutils.R;
import com.dizhongdi.serviceoss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:FileController
 * Package:com.dizhongdi.serviceoss.controller
 * Description:
 *
 * @Date: 2022/5/13 22:17
 * @Author:dizhongdi
 */

@Api(description="阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduoss/file")
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     */
    @PostMapping("upload")
    @ApiOperation(value = "文件上传")
    public R upload(@ApiParam(name = "file", value = "文件", required = true) @RequestParam("file") MultipartFile file){
        String url = fileService.upload(file);
        return R.ok().data("url",url);
    }

}
