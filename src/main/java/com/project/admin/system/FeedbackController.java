package com.project.admin.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.system.entity.Feedback;
import com.project.system.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 意见反馈控制器
 *
 * @author zhuyifa
 * @since 2019-08-08
 */
@RestController("/feedbackController2")
@RequestMapping("/admin/system/feedbacks")
public class FeedbackController extends BaseController {

    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 获取意见反馈
     *
     * @param pageEntity 分页实体
     * @param feedback   反馈实体
     * @return 操作结果
     */
    @GetMapping
    public JsonResult get(PageEntity<Feedback> pageEntity, Feedback feedback) {
        IPage<Feedback> page = feedbackService.pageByCondition(pageEntity.getMpPage(), feedback);
        return JsonResult.ok("获取意见反馈成功", page.getTotal(), page.getRecords());
    }
}
