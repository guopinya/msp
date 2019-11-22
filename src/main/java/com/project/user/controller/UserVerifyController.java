package com.project.user.controller;

import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.utils.SecurityUtils;
import com.project.user.entity.UserVerify;
import com.project.user.service.IUserVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户认证控制器
 *
 * @author zhuyifa
 * @since 2019-10-31
 */
@RestController
@RequestMapping("/user/users/verifies")
public class UserVerifyController extends BaseController {

    @Autowired
    private IUserVerifyService userVerifyService;

    /**
     * 获取用户认证
     *
     * @return 操作结果
     */
    @GetMapping
    public JsonResult obtain() {
        // 用户ID
        String userId = SecurityUtils.getUserId();
        // 用户认证信息
        UserVerify verify = userVerifyService.getById(userId);

        return JsonResult.ok("获取用户认证成功", verify);
    }

    /**
     * 添加用户认证
     *
     * @param verify    用户认证对象
     * @param frontFile 正面文件
     * @param backFile  反面文件
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addTo(UserVerify verify, MultipartFile frontFile, MultipartFile backFile) {
        // 用户ID
        String userId = SecurityUtils.getUserId();
        // 设置用户ID
        verify.setUserId(userId);
        // 设置身份证正面照
        verify.setIdCardFrontImage(upload(frontFile));
        // 设置身份证反面照
        verify.setIdCardBackImage(upload(backFile));
        // 保存用户认证
        userVerifyService.save(verify);

        return JsonResult.ok("添加用户认证成功", verify);
    }

    /**
     * 修改用户认证
     *
     * @param verify    用户认证对象
     * @param frontFile 正面文件
     * @param backFile  反面文件
     * @return 操作结果
     */
    @PutMapping
    public JsonResult modify(UserVerify verify, MultipartFile frontFile, MultipartFile backFile) {
        // 用户ID
        String userId = SecurityUtils.getUserId();
        // 设置用户ID
        verify.setUserId(userId);
        // 设置身份证正面照
        verify.setIdCardFrontImage(upload(frontFile));
        // 设置身份证反面照
        verify.setIdCardBackImage(upload(backFile));
        // 设置审核状态
        verify.setApprovalStatus(0);
        // 保存用户认证
        userVerifyService.save(verify);

        return JsonResult.ok("修改用户认证成功", verify);
    }
}

