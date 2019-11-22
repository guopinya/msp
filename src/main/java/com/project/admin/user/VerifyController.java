package com.project.admin.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.user.entity.UserVerify;
import com.project.user.service.IUserVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制器
 *
 * @author zhuyifa
 * @since 2019-08-09
 */
@RestController
@RequestMapping("/admin/user/verifies")
public class VerifyController extends BaseController {

    @Autowired
    private IUserVerifyService userVerifyService;

    /**
     * 获取认证
     *
     * @param pageEntity 分页实体
     * @param verify     认证实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult obtain(PageEntity<UserVerify> pageEntity, UserVerify verify) {
        IPage<UserVerify> page = userVerifyService.pageByVerifyCond(pageEntity.getMpPage(), verify);
        return JsonResult.ok("获取认证成功", page.getTotal(), page.getRecords());
    }

    /**
     * 修改认证
     *
     * @param verify 认证
     * @return JSON结果
     */
    @PutMapping
    public JsonResult getCareer(UserVerify verify) {
        userVerifyService.updateById(verify);
        return JsonResult.ok("修改认证成功", verify);
    }
}