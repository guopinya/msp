package com.project.msp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.msp.entity.Shop;
import com.project.msp.service.IShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("shopController")
@RequestMapping("/msp/shop")
public class ShopController extends BaseController {

    @Autowired
    private IShopService shopService;

    /**
     * 获取店铺
     *
     * @param pageEntity 分页实体
     * @param shop       店铺实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getShop(PageEntity<Shop> pageEntity, Shop shop) {
        IPage<Shop> page = shopService.pageByShopCond(pageEntity.getMpPage(), shop);
        return JsonResult.ok("获取店铺成功", page.getTotal(), page.getRecords());
    }
}

