package com.project.system.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.system.entity.Banner;
import com.project.system.entity.Menu;
import com.project.system.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 轮播图接口
 *
 * @author gpy
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/system/banners")
public class BannerController extends BaseController {

    @Autowired
    private IBannerService bannerService;

    /**
     * 获取轮播图
     *
     * @return 操作结果
     */
    @GetMapping
    public JsonResult getAllBanner(String id, String areaId) {
        if (id != null) {
            Banner banner = bannerService.getById(id);
            return JsonResult.ok("获取轮播图成功", banner);
        }

        Wrapper<Banner> wrapper = new QueryWrapper<Banner>().lambda()
                .eq(Banner::getAreaId, areaId)
                .orderByAsc(Banner::getSortNumber);
        return JsonResult.ok("获取所有轮播图成功", bannerService.list(wrapper));
    }
}