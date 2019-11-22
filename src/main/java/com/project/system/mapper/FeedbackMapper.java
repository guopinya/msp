package com.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.system.entity.Feedback;
import org.springframework.stereotype.Repository;

/**
 * 意见反馈映射器
 *
 * @author zhuyifa
 * @since 2019-10-24
 */
@Repository
public interface FeedbackMapper extends BaseMapper<Feedback> {

    /**
     * 按反馈条件查询分页
     *
     * @param page     分页条件
     * @param feedback 反馈条件
     * @return 反馈分页
     */
    IPage<Feedback> selectPageByCondition(IPage<Feedback> page, Feedback feedback);
}
