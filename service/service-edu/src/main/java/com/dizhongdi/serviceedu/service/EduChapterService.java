package com.dizhongdi.serviceedu.service;

import com.dizhongdi.serviceedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.serviceedu.vo.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-19
 */
public interface EduChapterService extends IService<EduChapter> {

//    嵌套章节数据列表
    List<ChapterVo> nestedList(String courseId);

    boolean removeChapterById(String id);

    //根据课程id删除所有章节
    boolean removeByCourseId(String id);
}
