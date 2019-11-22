package com.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.system.entity.Banner;

/**
 * 轮播图服务
 *
 * @author zhuyifa
 * @since 2019-08-08
 */
public interface IBannerService extends IService<Banner> {

    /**
     * 按轮播图条件查询分页
     *
     * @param page   分页条件
     * @param banner 轮播图条件
     * @return 轮播图分页
     */
    IPage<Banner> pageByBannerCond(IPage<Banner> page, Banner banner);
}
