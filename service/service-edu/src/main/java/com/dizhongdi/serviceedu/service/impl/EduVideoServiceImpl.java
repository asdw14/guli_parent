package com.dizhongdi.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dizhongdi.commonutils.R;
import com.dizhongdi.servicebase.exceptionhandler.GuliException;
import com.dizhongdi.serviceedu.client.VodClient;
import com.dizhongdi.serviceedu.entity.EduChapter;
import com.dizhongdi.serviceedu.entity.EduVideo;
import com.dizhongdi.serviceedu.mapper.EduVideoMapper;
import com.dizhongdi.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.serviceedu.vo.video.VideoInfoForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-19
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    //Feign服务调用
    @Autowired
    VodClient vodClient;

//    课时保存
    @Override
    public void saveVideoInfo(VideoInfoForm videoInfoForm) {
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm,video);
        //插入小节
        int insert = baseMapper.insert(video);
        if (insert<1){
            throw new GuliException(20001,"课时信息保存失败");
        }


    }

    //课时更新
    @Override
    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm,eduVideo);
        int i = baseMapper.updateById(eduVideo);
        if (i<1){
            throw new GuliException(20001, "课时信息更新失败");
        }
    }

    @Override
    public VideoInfoForm getVideoInfoFormById(String id) {
        EduVideo video = this.getById(id);
        if (video!=null) {
            VideoInfoForm videoInfoForm = new VideoInfoForm();
            BeanUtils.copyProperties(video, videoInfoForm);
            return videoInfoForm;
        }else {
            throw new GuliException(20001, "数据不存在");
        }
    }

    //通过课程id删除所有小节视频
    @Override
    public boolean removeByCourseId(String courseId) {
        //远程调用根据云端视频id列表删除所有视频
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduVideo> videos = baseMapper.selectList(wrapper);
        ArrayList<String> list = new ArrayList<>();
        //得到所有视频列表的云端原始视频id
        for (EduVideo video: videos) {
            if(!StringUtils.isEmpty(video.getVideoSourceId())){
                list.add(video.getVideoSourceId());
            }
        }
        //调用vod服务删除远程视频
        if (list.size()>0){
            R r = vodClient.removeVideoList(list);
            if (r.getCode()==20001){
                throw new GuliException(20001,"删除失败，服务垮了");
            }
        }
        //删除video表的记录
        return baseMapper.delete(new QueryWrapper<EduVideo>().eq("course_id",courseId)) > 0 ? true : false;
    }

    //根据id删除课时，并删除云端视频资源
    @Override
    public boolean removeVideoById(String id) {
        EduVideo video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        //如果视频资源存在则删除
        if (!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }
        //如果删除的返回结果大于0条说明删除成功
        return baseMapper.deleteById(id) > 0 ? true : false;
    }
}
