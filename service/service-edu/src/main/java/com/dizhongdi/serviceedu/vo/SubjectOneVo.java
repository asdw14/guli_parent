package com.dizhongdi.serviceedu.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:SubjectVo
 * Package:com.dizhongdi.serviceedu.vo
 * Description:
 *
 * @Date: 2022/6/19 0:34
 * @Author:dizhongdi
 */
@Data
@Accessors(chain = true)
public class SubjectOneVo {
    private String id;
    private String title;
    private List<SubjectTwoVo> children = new ArrayList<>();

}
