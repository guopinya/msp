package com.project.sq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.sq.entity.Topic;

import java.util.List;

/**
 * 话题服务
 *
 * @author zhuyifa
 */
public interface ITopicService extends IService<Topic> {

    /**
     * 通过实体参数获取列表
     *
     * @param p 话题对象
     * @return 获取到的话题列表对象
     */
    List<Topic> list(Topic p);
}
