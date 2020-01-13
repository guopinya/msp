package com.project.sq.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.utils.SecurityUtils;
import com.project.sq.entity.Follow;
import com.project.sq.service.IFollowService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关注控制器
 *
 * @author zhuyifa
 */
@RestController
@RequestMapping(value = "/sq/follows")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class FollowController extends BaseController {

    private final IFollowService followService;

    /**
     * 添加关注
     *
     * @param p 关注对象
     * @return api请求结果
     */
    @PostMapping
    public JsonResult addTo(Follow p) {
        // 登录用户
        String userId = SecurityUtils.getUserId();

        // 设置用户ID
        p.setUserId(userId);
        // 保存关注
        followService.save(p);

        return JsonResult.ok("添加关注成功", null);
    }

    /**
     * 删除关注
     *
     * @param p 关注对象
     * @return api请求结果
     */
    @DeleteMapping
    public JsonResult delete(Follow p) {
        // 登录用户
        String userId = SecurityUtils.getUserId();

        // 设置用户ID
        p.setUserId(userId);
        // 删除关注
        followService.remove(p);

        return JsonResult.ok("删除关注成功", null);
    }

}
