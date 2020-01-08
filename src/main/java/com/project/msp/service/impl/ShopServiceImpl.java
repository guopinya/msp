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
        String areaId = shop.getAreaId();
        String shopParentId0 = shop.getShopParentId0();
        String shopParentId1 = shop.getShopParentId1();
        String shopParentId2 = shop.getShopParentId2();
        String shopParentId3 = shop.getShopParentId3();
        String shopName = shop.getShopName();
        String shopNo = shop.getShopNo();
        String shopState = shop.getShopState();
        String shopIsHot = shop.getShopIsHot();

        Wrapper<Shop> wrapper = new QueryWrapper<Shop>().lambda()
                .eq(StringUtils.isNotBlank(areaId), Shop::getAreaId, areaId)
                .eq(StringUtils.isNotBlank(shopParentId0), Shop::getShopParentId3, shopParentId0)
                .eq(StringUtils.isNotBlank(shopParentId1), Shop::getShopParentId3, shopParentId1)
                .eq(StringUtils.isNotBlank(shopParentId2), Shop::getShopParentId3, shopParentId2)
                .eq(StringUtils.isNotBlank(shopParentId3), Shop::getShopParentId3, shopParentId3)
                .like(StringUtils.isNotBlank(shopName), Shop::getShopName, shopName)
                .like(StringUtils.isNotBlank(shopNo), Shop::getShopNo, shopNo)
                .eq(StringUtils.isNotBlank(shopState), Shop::getShopState, shopState)
                .eq(StringUtils.isNotBlank(shopIsHot), Shop::getShopIsHot, shopIsHot);
        return shopMapper.selectPage(page, wrapper);
    }
}