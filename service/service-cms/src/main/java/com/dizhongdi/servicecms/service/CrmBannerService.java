package com.dizhongdi.servicecms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.servicecms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-04
 */
public interface CrmBannerService extends IService<CrmBanner> {
    //    获取Banner分页列表
    IPage pageBanner(Page<CrmBanner> crmBannerPage);

    //获取首页banner
    List<CrmBanner> selectIndexList();

    //添加banner
    void saveBanner(CrmBanner crmBanner);

}
