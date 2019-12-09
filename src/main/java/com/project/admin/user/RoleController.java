package com.project.admin.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.system.entity.Role;
import com.project.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色控制器
 *
 * @author zhuyifa
 * @date 2019-10-16
 */
@RestController
@RequestMapping("/admin/user/roles")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    /**
     * 获取用户角色
     *
     * @param pageEntity 分页实体
     * @param role       角色实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getRoles(PageEntity<Role> pageEntity, Role role) {
        if (pageEntity.isEmpty()) {
            return JsonResult.ok("获取用户角色成功", roleService.list());
        }

        IPage<Role> page = roleService.pageByRoleCond(pageEntity.getMpPage(), role);
        return JsonResult.ok("获取用户角色成功", page.getTotal(), page.getRecords());
    }

    /**
     * 添加用户角色
     *
     * @param role 角色
     * @return JSON结果
     */
    @PostMapping
    public JsonResult addToRole(Role role) {
        role.setIsSet("no");
        roleService.save(role);
        return JsonResult.ok("添加用户角色成功", null);
    }

    /**
     * 修改用户角色
     *
     * @param role 角色
     * @return JSON结果
     */
    @PutMapping
    public JsonResult modifyRole(Role role) {
        if (Role.DEFAULT_ID_DISTRICT.equals(role.getId()) || Role.DEFAULT_ID_AGENT.equals(role.getId())
                || Role.DEFAULT_ID_SUPERVISION.equals(role.getId()) || Role.DEFAULT_ID_FRANCHISEE.equals(role.getId())) {
            return JsonResult.fail("固定身份角色不能修改哟", null);
        }
        role.setIsSet("no");
        roleService.updateById(role);
        return JsonResult.ok("修改用户角色成功", null);
    }
}
