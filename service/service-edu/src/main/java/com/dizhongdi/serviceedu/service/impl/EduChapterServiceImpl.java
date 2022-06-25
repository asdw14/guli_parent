package com.dizhongdi.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dizhongdi.servicebase.exceptionhandler.GuliException;
import com.dizhongdi.serviceedu.entity.EduChapter;
import com.dizhongdi.serviceedu.entity.EduVideo;
import com.dizhongdi.serviceedu.mapper.EduChapterMapper;
import com.dizhongdi.serviceedu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.serviceedu.service.EduVideoService;
import com.dizhongdi.serviceedu.vo.chapter.ChapterVo;
import com.dizhongdi.serviceedu.vo.chapter.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-19
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService eduVideoService;

    //嵌套章节数据列表
    @Override
    public List<ChapterVo> nestedList(String courseId) {
        //最终要的到的数据列表
        ArrayList<ChapterVo> chapterList = new ArrayList<>();

        //获取章节信息
        List<EduChapter> chapters = baseMapper.selectList(new QueryWrapper<EduChapter>().eq("course_id", courseId).orderByAsc("sort"));

        //获取小节信息
        List<EduVideo> videoList = eduVideoService.list(new QueryWrapper<EduVideo>().orderByAsc("sort"));

        //填充章节vo数据
        for (EduChapter eduChapter : chapters) {
            //创建章节vo对象
            ChapterVo chapterVo = new ChapterVo();

            //创建课时vo对象
            ArrayList<VideoVo> children = new ArrayList<>();

            //填充课时vo数据
            BeanUtils.copyProperties(eduChapter,chapterVo);
            for (EduVideo video: videoList) {
                if (video.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    children.add(videoVo);
                }
            }
            //填充章节里的小节
            chapterVo.setChildren(children);

            //把对应的章节添加到集合里返回
            chapterList.add(chapterVo);
        }
        

        return chapterList;
    }

//    根据ID删除章节
    @Override
    public boolean removeChapterById(String id) {
        List<EduVideo> videoList = eduVideoService.list(new QueryWrapper<EduVideo>().eq("chapter_id", id));
        if (videoList.size()>0){
            throw new GuliException(20001,"该分章节下存在视频课程，请先删除视频课程");
        }
        int i = baseMapper.deleteById(id);
        if (i>0){
            return true;
        }
        return false;

    }

    @Override
    public boolean removeByCourseId(String id) {
        return baseMapper.delete(new QueryWrapper<EduChapter>().eq("course_id",id)) > 0 ? true : false;

    }
}
