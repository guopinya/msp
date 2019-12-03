package com.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.system.entity.Banner;
import com.project.system.mapper.BannerMapper;
import com.project.system.service.IBannerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 轮播图服务实现
 *
 * @author zhuyifa
 * @since 2019-08-08
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 按轮播图条件查询分页
     *
     * @param page   分页条件
     * @param banner 轮播图条件
     * @return 轮播图分页
     */
    @Override
    public IPage<Banner> pageByBannerCond(IPage<Banner> page, Banner banner) {
        // 轮播图类型
        String type = banner.getType();
        // 轮播图标题
        String title = banner.getTitle();

        Wrapper<Banner> wrapper = new QueryWrapper<Banner>().lambda()
                .eq(StringUtils.isNotBlank(type), Banner::getType, type)
                .like(StringUtils.isNotBlank(title), Banner::getTitle, title);
        return bannerMapper.pageByBannerCond(page, wrapper);
    }
}
