package com.project.admin.system;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.system.entity.Menu;
import com.project.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单控制器
 *
 * @author zhuyifa
 * @since 2019-07-25
 */
@RestController
@RequestMapping("/admin/system/menus")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    /**
     * 获取菜单
     *
     * @return JSON结果
     */
    @GetMapping
    public JsonResult get() {
        Wrapper<Menu> wrapper = new QueryWrapper<Menu>().lambda()
                .orderByAsc(Menu::getSortNumber);
        return JsonResult.ok("获取菜单成功", menuService.list(wrapper));
    }

    /**
     * 获取菜单树
     *
     * @return JSON结果
     */
    @GetMapping("/tree")
    public JsonResult getTree(String userId) {
        return JsonResult.ok("获取菜单树成功", menuService.listByUserId(userId));
    }

    /**
     * 添加菜单
     *
     * @param menu 菜单实体
     * @return JSON结果
     */
    @PostMapping
    public JsonResult addTo(Menu menu) {
        return JsonResult.ok("添加菜单成功", menuService.save(menu));
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单实体
     * @return JSON结果
     */
    @PutMapping
    public JsonResult modify(Menu menu) {
        return JsonResult.ok("修改菜单成功", menuService.updateById(menu));
    }
}

