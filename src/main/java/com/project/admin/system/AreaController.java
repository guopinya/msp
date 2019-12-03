package com.project.admin.system;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.system.entity.Area;
import com.project.system.service.IAreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 地区控制器
 *
 * @author zhuyifa
 * @since 2019-08-08
 */
@RestController
@RequestMapping("/admin/system/area")
public class AreaController extends BaseController {

    @Autowired
    private IAreaService areaService;

    /**
     * 获取地区
     *
     * @param area 地区实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getArea(Area area) {
        String name = area.getAreaName();
        String code = area.getAreaCode();
        String isHot = area.getIsHot();
        Wrapper<Area> wrapper = new QueryWrapper<Area>().lambda().orderByAsc(Area::getSortNumber)
                .like(StringUtils.isNotBlank(name), Area::getAreaName, name)
                .like(StringUtils.isNotBlank(code), Area::getAreaCode, code)
                .eq(StringUtils.isNotBlank(isHot), Area::getIsHot, isHot);
        return JsonResult.ok("获取菜单成功", areaService.list(wrapper));
    }

    /**
     * 添加地区
     *
     * @param area 地区
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addArea(Area area) {
        areaService.save(area);
        return JsonResult.ok("添加地区成功", area);
    }

    /**
     * 修改地区
     *
     * @param area 地区
     * @return 操作结果
     */
    @PutMapping
    public JsonResult modifyArea(Area area) {
        areaService.updateById(area);
        return JsonResult.ok("修改地区成功", area);
    }

    /**
     * 删除地区
     *
     * @param areaId 地区ID
     * @return 操作结果
     */
    @DeleteMapping("/{areaId}")
    public JsonResult deleteMenu(@PathVariable String areaId) {
        areaService.removeById(areaId);
        return JsonResult.ok("删除地区成功", null);
    }
}

