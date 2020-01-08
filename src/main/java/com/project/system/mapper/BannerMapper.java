package com.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.system.entity.Banner;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 轮播图映射器
 *
 * @author zhuyifa
 * @since 2019-08-08
 */
@Repository
public interface BannerMapper extends BaseMapper<Banner> {

    /**
     * 通过拍品条件查询分页
     *
     * @param page   分页条件
     * @param banner 轮播条件
     * @return 拍品分页
     */
    IPage<Banner> pageByBannerCond(IPage<Banner> page, Banner banner);
}
