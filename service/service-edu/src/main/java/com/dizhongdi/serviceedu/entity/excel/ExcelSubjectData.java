package com.dizhongdi.serviceedu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * ClassName:ExcelSubjectData
 * Package:com.dizhongdi.serviceedu.entity.excel
 * Description:
 *
 * @Date: 2022/6/15 23:46
 * @Author:dizhongdi
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}