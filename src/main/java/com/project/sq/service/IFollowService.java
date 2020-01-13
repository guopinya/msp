package com.project.sq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.sq.entity.Circle;
import com.project.sq.entity.Follow;

/**
 * 关注服务
 *
 * @author zhuyifa
 */
public interface IFollowService extends IService<Follow> {

    /**
     * 删除关注
     *
     * @param p 关注对象
     * @return 是否成功
     */
    boolean remove(Follow p);
}
