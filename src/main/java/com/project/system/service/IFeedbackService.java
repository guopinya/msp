package com.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.system.entity.Feedback;

/**
 * 意见反馈服务
 *
 * @author zhuyifa
 * @since 2019-10-24
 */
public interface IFeedbackService extends IService<Feedback> {

    /**
     * 按反馈条件查询分页
     *
     * @param page     分页条件
     * @param feedback 反馈条件
     * @return 反馈分页
     */
    IPage<Feedback> pageByCondition(IPage<Feedback> page, Feedback feedback);
}
