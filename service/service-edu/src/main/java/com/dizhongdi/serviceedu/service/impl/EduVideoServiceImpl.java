package com.dizhongdi.serviceedu.service.impl;

import com.dizhongdi.servicebase.exceptionhandler.GuliException;
import com.dizhongdi.serviceedu.entity.EduVideo;
import com.dizhongdi.serviceedu.mapper.EduVideoMapper;
import com.dizhongdi.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.serviceedu.vo.video.VideoInfoForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}
