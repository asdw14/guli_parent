package com.dizhongdi.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dizhongdi.servicebase.exceptionhandler.GuliException;
import com.dizhongdi.serviceedu.entity.EduSubject;
import com.dizhongdi.serviceedu.entity.excel.ExcelSubjectData;
import com.dizhongdi.serviceedu.listener.SubjectExcelListener;
import com.dizhongdi.serviceedu.mapper.EduSubjectMapper;
import com.dizhongdi.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.serviceedu.vo.SubjectOneVo;
import com.dizhongdi.serviceedu.vo.SubjectTwoVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService subjectService) {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            throw new GuliException(20002,"添加课程分类失败");
        }
    }

    //获取嵌套课程list列表
    @Override
    public List<SubjectOneVo> nestedList() {
        long start = System.currentTimeMillis();
        ArrayList<SubjectOneVo> subjectOneVos = new ArrayList<>();
        List<EduSubject> eduSubjectList = baseMapper.selectList(new QueryWrapper<EduSubject>().eq("parent_id", "0"));
        for (EduSubject eduSubject: eduSubjectList) {
            List<EduSubject> subjectTwoList = baseMapper.selectList(new QueryWrapper<EduSubject>().eq("parent_id", eduSubject.getId()));
            ArrayList<SubjectTwoVo> twoVos = new ArrayList<>();
            for (EduSubject eduSubject1 : subjectTwoList) {
                twoVos.add(new SubjectTwoVo().setId(eduSubject1.getId()).setTitle(eduSubject1.getTitle()));
            }
            subjectOneVos.add(new SubjectOneVo().setId(eduSubject.getId()).setTitle(eduSubject.getTitle()).setChildren(twoVos));
        }
        long end = System.currentTimeMillis();

        System.out.println("运行时间："+(end-start));
        return subjectOneVos;
    }
}
