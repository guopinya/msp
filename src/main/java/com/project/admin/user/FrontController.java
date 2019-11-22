package com.project.admin.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.user.entity.User;
import com.project.user.entity.UserAuth;
import com.project.user.entity.UserCareer;
import com.project.user.service.IUserAuthService;
import com.project.user.service.IUserCareerService;
import com.project.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 前端用户
 *
 * @author zhuyifa
 * @since 2019-10-16
 */
@RestController
@RequestMapping("/admin/user/fronts")
public class FrontController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserAuthService userAuthService;
    @Autowired
    private IUserCareerService userCareerService;

    /**
     * 获取前端用户
     *
     * @param pageEntity 分页实体
     * @param user       用户实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getUsers(PageEntity<User> pageEntity, User user) {
        if (pageEntity.isEmpty()) {
            return JsonResult.ok("获取前端用户成功", userService.list());
        }
        IPage<User> page = userService.pageByUserCond(pageEntity.getMpPage(), user);
        return JsonResult.ok("获取前端用户成功", page.getTotal(), page.getRecords());
    }

    /**
     * 获取用户授权
     *
     * @param userId 用户ID
     * @return JSON结果
     */
    @GetMapping("/userAuth")
    public JsonResult getUserAuth(String userId) {
        return JsonResult.ok("获取密码成功", userAuthService.getByUserIdAndAuthType(userId, UserAuth.TYPE_PLAINTEXT));
    }

    /**
     * 获取用户职业
     *
     * @param userId 用户ID
     * @return JSON结果
     */
    @GetMapping("/userCareer")
    public JsonResult getUserCareer(String userId) {
        return JsonResult.ok("获取用户职业成功", userCareerService.getByUserId(userId));
    }

    /**
     * 添加前端用户
     *
     * @param user 用户
     * @return JSON结果
     */
    @PostMapping
    public JsonResult addToUser(User user, String password) {
        // 检查头像文件
        MultipartFile avatarFile = user.getAvatarFile();
        if (avatarFile != null) {
            user.setAvatarPath(upload(avatarFile));
        } else {
            user.setAvatarPath(User.VAL_USER_AVATAR_DEFAULT);
        }

        user.setUserType("1");
        // 添加用户
        userService.registerUser(user, password);
        return JsonResult.ok("添加前端用户成功", null);
    }

    /**
     * 修改前端用户
     *
     * @param user 用户
     * @return JSON结果
     */
    @PutMapping
    public JsonResult modifyUser(User user, String password) {
        // 检查头像文件
        MultipartFile avatarFile = user.getAvatarFile();
        if (avatarFile != null) {
            user.setAvatarPath(upload(avatarFile));
        }

        // 更新用户
        userService.updateById(user);
        // 更新用户密码
        if (password != null) {
            userAuthService.update(user.getUserId(), UserAuth.TYPE_PASSWORD, password);
            userAuthService.update(user.getUserId(), UserAuth.TYPE_PLAINTEXT, password);
        }
        return JsonResult.ok("修改前端用户成功", null);
    }

    /**
     * 修改用户职业
     *
     * @param userCareer 用户职业
     * @return JSON结果
     */
    @PutMapping("/userCareer")
    public JsonResult modifyUserCareer(UserCareer userCareer) {
        // 更新用户
        userCareerService.updateById(userCareer);
        return JsonResult.ok("修改用户职业成功", null);
    }
}
