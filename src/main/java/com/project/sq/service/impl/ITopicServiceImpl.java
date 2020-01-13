package com.project.sq.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.sq.entity.Topic;
import com.project.sq.mapper.TopicMapper;
import com.project.sq.service.ITopicService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 话题服务实现
 *
 * @author zhuyifa
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ITopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {

    private final TopicMapper topicMapper;

    @Override
    public List<Topic> list(Topic p) {
        // 父话题ID
        String parentId = p.getParentId();
        if (parentId == null) {
            parentId = Topic.DEFAULT_PARENT;
        }

        // 是否按话题名称模糊查询
        String topicName = p.getName();
        // 是否执行模糊查询
        boolean cond = topicName != null &&
                !Topic.DEFAULT_PARENT.equals(parentId);

        // 条件构造器
        Wrapper<Topic> wrapper = new LambdaQueryWrapper<Topic>()
                // 父话题ID
                .eq(Topic::getParentId, parentId)
                // 话题名称模糊查询
                .like(cond, Topic::getName, topicName)
                // 通过sortNumber排序
                .orderByAsc(Topic::getSortNumber);

        return topicMapper.selectList(wrapper);
    }

}
