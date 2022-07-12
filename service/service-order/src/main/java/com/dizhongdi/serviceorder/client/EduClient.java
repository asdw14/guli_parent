package com.dizhongdi.serviceorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName:EduClient
 * Package:com.dizhongdi.serviceorder.client
 * Description:
 *
 * @Date: 2022/7/11 23:14
 * @Author:dizhongdi
 */
@Component
@FeignClient("service-edu")
public interface EduClient {
    //根据课程id查询课程信息
    @GetMapping("/eduservice/course/getDto/{courseId}")
    public com.dizhongdi.commonutils.vo.CourseWebVoOrder getCourseInfoDto(@PathVariable("courseId") String courseId);
}
