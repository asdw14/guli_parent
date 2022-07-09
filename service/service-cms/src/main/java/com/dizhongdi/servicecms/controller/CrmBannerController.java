package com.dizhongdi.servicecms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.commonutils.R;
import com.dizhongdi.servicecms.entity.BannerInfoVo;
import com.dizhongdi.servicecms.entity.CrmBanner;
import com.dizhongdi.servicecms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author dizhongdi
 * @since 2022-07-04
 */
@RestController
@RequestMapping("/educms/banner")
@CrossOrigin
public class CrmBannerController {

    @Autowired
    CrmBannerService bannerService;
    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        IPage pageParam = bannerService.pageBanner(new Page<CrmBanner>(page, limit));
        return R.ok().data("items",pageParam.getRecords()).data("total",pageParam.getTotal());
    }

    @ApiOperation(value = "获取Banner列表")
    @Cacheable(value = "banner", key = "'findAll'")
    @GetMapping("findAll")
    public R findAll() {
        return R.ok().data("items",bannerService.list(new QueryWrapper<CrmBanner>()));
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item",banner);

    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R update(@RequestBody CrmBanner crmBanner) {
        redisTemplate.delete("banner::findAll");
        bannerService.updateById(crmBanner);
        redisTemplate.delete("banner::findAll");
        return R.ok();
    }

    @ApiOperation(value = "添加Banner")
    @PostMapping("save")
    public R save(@RequestBody BannerInfoVo infoVo) {
        redisTemplate.delete("banner::findAll");
        CrmBanner crmBanner = new CrmBanner();
        BeanUtils.copyProperties(infoVo,crmBanner);
        bannerService.saveBanner(crmBanner);
        redisTemplate.delete("banner::findAll");
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }
}

