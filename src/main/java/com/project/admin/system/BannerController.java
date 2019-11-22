package com.project.admin.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.system.entity.Banner;
import com.project.system.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 轮播图控制器
 *
 * @author zhuyifa
 * @since 2019-08-08
 */
@RestController("bannerController2")
@RequestMapping("/admin/system/banners")
public class BannerController extends BaseController {

    @Autowired
    private IBannerService bannerService;

    /**
     * 获取轮播图
     *
     * @param pageEntity 分页实体
     * @param banner     轮播图实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getBanner(PageEntity<Banner> pageEntity, Banner banner) {
        IPage<Banner> page = bannerService.pageByBannerCond(pageEntity.getMpPage(), banner);
        return JsonResult.ok("获取轮播图成功", page.getTotal(), page.getRecords());
    }

    /**
     * 添加轮播图
     *
     * @param banner    轮播图
     * @param imageFile 图片文件
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addBanner(Banner banner, MultipartFile imageFile) {
        banner.setImage(upload(imageFile));
        bannerService.save(banner);
        return JsonResult.ok("添加轮播图成功", banner);
    }

    /**
     * 修改轮播图
     *
     * @param banner    轮播图
     * @param imageFile 图片文件
     * @return 操作结果
     */
    @PutMapping
    public JsonResult modifyBanner(Banner banner, MultipartFile imageFile) {
        banner.setImage(upload(imageFile));
        bannerService.updateById(banner);
        return JsonResult.ok("修改轮播图成功", banner);
    }

    /**
     * 删除轮播图
     *
     * @param bannerId 轮播图ID
     * @return 操作结果
     */
    @DeleteMapping("/{bannerId}")
    public JsonResult deleteMenu(@PathVariable String bannerId) {
        bannerService.removeById(bannerId);
        return JsonResult.ok("删除轮播图成功", null);
    }
}

