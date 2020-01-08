package com.project.msp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.msp.entity.Shop;

public interface IShopService extends IService<Shop> {

    /**
     * 按条件查询分页
     *
     * @param page 分页条件
     * @param shop 条件
     * @return 分页
     */
    IPage<Shop> pageByShopCond(IPage<Shop> page, Shop shop);
}
