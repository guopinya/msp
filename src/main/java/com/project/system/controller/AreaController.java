package com.project.system.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.system.entity.Area;
import com.project.system.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 地区接口
 *
 * @author gpy
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/system/areas")
public class AreaController extends BaseController {

    @Autowired
    private IAreaService areaService;

    /**
     * 获取地区
     *
     * @return 操作结果
     */
    @GetMapping
    public JsonResult getAllArea() {
        Wrapper<Area> wrapper = new QueryWrapper<Area>().lambda()
                .orderByAsc(Area::getSortNumber);
        return JsonResult.ok("获取所有地区成功", areaService.list(wrapper));
    }
}