package com.project.msp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.msp.entity.Shop;
import com.project.msp.mapper.ShopMapper;
import com.project.msp.service.IShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Autowired
    private ShopMapper shopMapper;

    /**
     * 按条件查询分页
     *
     * @param page 分页条件
     * @param shop 条件
     * @return 分页
     */
    @Override
    public IPage<Shop> pageByShopCond(IPage<Shop> page, Shop shop) {
        String no = shop.getNo();
        String shopName = shop.getShopName();
        String isHot = shop.getIsHot();
        String state = shop.getState();

        Wrapper<Shop> wrapper = new QueryWrapper<Shop>().lambda()
                .eq(StringUtils.isNotBlank(no), Shop::getNo, no)
                .like(StringUtils.isNotBlank(shopName), Shop::getShopName, shopName)
                .eq(StringUtils.isNotBlank(isHot), Shop::getIsHot, isHot)
                .eq(StringUtils.isNotBlank(state), Shop::getState, state);
        return shopMapper.selectPage(page, wrapper);
    }
}