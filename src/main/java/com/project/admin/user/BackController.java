package com.project.admin.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.system.entity.SysUser;
import com.project.system.service.ISysUserService;
import com.project.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * 后端用户
 *
 * @author zhuyifa
 * @since 2019-10-16
 */
@RestController
@RequestMapping("/admin/user/backs")
public class BackController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 获取后端用户
     *
     * @param pageEntity 分页实体
     * @param user       用户实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getUsers(PageEntity<SysUser> pageEntity, SysUser user) {
        IPage<SysUser> page = sysUserService.pageByUserCond(pageEntity.getMpPage(), user);
        return JsonResult.ok("获取后端用户成功", page.getTotal(), page.getRecords());
    }

    /**
     * 添加后端用户
     *
     * @param user       用户
     * @param avatarFile 头像文件
     * @return JSON结果
     */
    @PostMapping
    public JsonResult addToUser(SysUser user, MultipartFile avatarFile) {
        // 检查头像文件
        if (avatarFile != null) {
            user.setAvatar(upload(avatarFile));
        } else {
            user.setAvatar(User.VAL_USER_AVATAR_DEFAULT);
        }

        user.setStatus("normal");
        user.setCreateTime(LocalDateTime.now());
        // 添加用户
        sysUserService.save(user);
        return JsonResult.ok("添加后端用户成功", null);
    }

    /**
     * 修改后端用户
     *
     * @param user       用户
     * @param avatarFile 头像文件
     * @return JSON结果
     */
    @PutMapping
    public JsonResult modifyUser(SysUser user, MultipartFile avatarFile) {
        // 检查头像文件
        if (avatarFile != null) {
            user.setAvatar(upload(avatarFile));
        }

        // 更新用户
        sysUserService.updateById(user);
        return JsonResult.ok("修改后端用户成功", null);
    }
}
