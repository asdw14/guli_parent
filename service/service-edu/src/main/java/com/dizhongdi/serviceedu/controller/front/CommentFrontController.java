package com.dizhongdi.serviceedu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.commonutils.JwtUtils;
import com.dizhongdi.commonutils.R;
import com.dizhongdi.commonutils.vo.UcenterMember;
import com.dizhongdi.serviceedu.client.UcenterClient;
import com.dizhongdi.serviceedu.entity.EduComment;
import com.dizhongdi.serviceedu.service.EduCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:CommentFrontController
 * Package:com.dizhongdi.serviceedu.controller.front
 * Description:
 *
 * @Date: 2022/7/10 22:51
 * @Author:dizhongdi
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class CommentFrontController {
    @Autowired
    private EduCommentService commentService;
    @Autowired
    private UcenterClient ucenterClient;

    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    String courseId) {

        Page<EduComment> pageParam = new Page<>(page, limit);
        commentService.page(pageParam, new QueryWrapper<EduComment>().eq("course_id", courseId));

        List<EduComment> commentList = pageParam.getRecords();
        Map<String, Object> map = new HashMap<>();

        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return R.ok().data(map);

    }

    @ApiOperation(value = "添加评论")
    @PostMapping("auth/save")
    public R save(@RequestBody EduComment comment, HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(id)) {
            return R.error().code(28004).message("请登录");
        }
        comment.setMemberId(id);

        UcenterMember ucenterMember = ucenterClient.getInfo(id);
        System.out.println(ucenterMember.toString());
        comment.setNickname(ucenterMember.getNickname());
        comment.setAvatar(ucenterMember.getAvatar());

        commentService.save(comment);
        return R.ok();
    }
}
