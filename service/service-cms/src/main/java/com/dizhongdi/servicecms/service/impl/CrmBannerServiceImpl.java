package com.dizhongdi.servicecms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.servicecms.entity.CrmBanner;
import com.dizhongdi.servicecms.mapper.CrmBannerMapper;
import com.dizhongdi.servicecms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-04
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

//    获取Banner分页列表
    @Override
    public IPage pageBanner(Page<CrmBanner> crmBannerPage) {
        IPage<CrmBanner> pageParam = this.page(crmBannerPage, new QueryWrapper<CrmBanner>());
        return pageParam;
    }

    //获取首页banner
    @Override
    @Cacheable(value = "banner", key = "'selectIndexList'")
    public List<CrmBanner> selectIndexList() {
        List<CrmBanner> crmBanners = baseMapper.selectList(new QueryWrapper<CrmBanner>().orderByDesc("sort"));
        return crmBanners;
    }

    //添加banner
    @Override
    public void saveBanner(CrmBanner crmBanner) {
        if (StringUtils.isEmpty(crmBanner.getSort())){
            crmBanner.setSort(0);
        }
        baseMapper.insert(crmBanner);
    }
}
