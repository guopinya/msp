package com.project.admin.msp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.msp.entity.Shop;
import com.project.msp.service.IShopService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("shopController_admin")
@RequestMapping("/admin/msp/shop")
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

    /**
     * 添加店铺
     *
     * @param shop        店铺
     * @param imageFile   图片文件
     * @param bannerFiles 图片组文件
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addShop(Shop shop, MultipartFile imageFile, List<MultipartFile> bannerFiles) {
        shop.setShopImage(upload(imageFile));
        shop.setShopBanner(upload(bannerFiles));
        String sTime = shop.getShopStime();
        if (StringUtils.isNotBlank(sTime)) {
            shop.setShopStime(sTime.split(" - ")[0]);
            shop.setShopEtime(sTime.split(" - ")[1]);
        }
        shopService.save(shop);
        return JsonResult.ok("添加店铺成功", shop);
    }

    /**
     * 修改店铺
     *
     * @param shop        店铺
     * @param imageFile   图片文件
     * @param bannerFiles 图片组文件
     * @return 操作结果
     */
    @PutMapping
    public JsonResult modifyShop(Shop shop, MultipartFile imageFile, List<MultipartFile> bannerFiles) {
        shop.setShopImage(upload(imageFile));
        shop.setShopBanner(assembleFiles(upload(bannerFiles), shop.getShopBanner()));
        String sTime = shop.getShopStime();
        if (StringUtils.isNotBlank(sTime)) {
            shop.setShopStime(sTime.split(" - ")[0]);
            shop.setShopEtime(sTime.split(" - ")[1]);
        }
        shopService.updateById(shop);
        return JsonResult.ok("修改店铺成功", shop);
    }

    /**
     * 修改店铺状态
     *
     * @param shop 店铺
     * @return 操作结果
     */
    @PutMapping("/state")
    public JsonResult modifyShopState(Shop shop) {
        shop = shopService.getById(shop.getId());
        if ("open".equals(shop.getShopState())) {
            shop.setShopState("off");
            shop.setShopStateFlag("manual");
        } else {
            shop.setShopState("open");
            shop.setShopStateFlag("auto");
        }
        shopService.updateById(shop);
        return JsonResult.ok("修改店铺成功", shop);
    }

    /**
     * 删除店铺
     *
     * @param shopId 店铺ID
     * @return 操作结果
     */
    @DeleteMapping("/{shopId}")
    public JsonResult deleteMenu(@PathVariable String shopId) {
        shopService.removeById(shopId);
        return JsonResult.ok("删除店铺成功", null);
    }
}

