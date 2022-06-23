package com.dizhongdi.serviceedu.service;

import com.dizhongdi.serviceedu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.serviceedu.vo.video.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-06-19
 */
public interface EduVideoService extends IService<EduVideo> {

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);
}
