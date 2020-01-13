package com.project.sq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.sq.entity.Follow;
import org.springframework.stereotype.Repository;

/**
 * 关注映射器
 *
 * @author zhuyifa
 */
@Repository
public interface FollowMapper extends BaseMapper<Follow> {

    /**
     * 查询是否存在
     *
     * @param userId   用户ID
     * @param targetId 关注对象ID
     * @return 是否存在
     */
    boolean exists(String userId, String targetId);

}