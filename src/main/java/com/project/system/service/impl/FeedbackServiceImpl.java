package com.project.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.system.entity.Feedback;
import com.project.system.mapper.FeedbackMapper;
import com.project.system.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 意见反馈服务实现
 *
 * @author zhuyifa
 * @since 2019-10-24
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 按反馈条件查询分页
     *
     * @param page     分页条件
     * @param feedback 反馈条件
     * @return 反馈分页
     */
    @Override
    public IPage<Feedback> pageByCondition(IPage<Feedback> page, Feedback feedback) {
        return feedbackMapper.selectPageByCondition(page, feedback);
    }
}
