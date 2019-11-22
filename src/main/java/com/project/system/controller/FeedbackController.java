package com.project.system.controller;

import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.exception.InspectException;
import com.project.common.utils.SecurityUtils;
import com.project.system.entity.Feedback;
import com.project.system.service.IFeedbackService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 意见反馈控制器
 *
 * @author zhuyifa
 * @since 2019-10-29
 */
@RestController
@RequestMapping("/system/feedbacks")
public class FeedbackController extends BaseController {

    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 添加意见反馈
     *
     * @param feedback 反馈实体
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addTo(Feedback feedback) {
        // 设置用户ID
        feedback.setUserId(SecurityUtils.getUserId());
        // 设置创建时间
        feedback.setCreateTime(LocalDateTime.now());
        // 检查反馈内容
        if (StringUtils.isEmpty(feedback.getContent())) {
            throw new InspectException("反馈内容不能为空");
        }
        // 保存意见反馈
        feedbackService.save(feedback);
        return JsonResult.ok("添加意见反馈成功", feedback);
    }
}