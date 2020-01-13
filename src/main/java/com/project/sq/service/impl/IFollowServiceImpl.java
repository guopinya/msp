package com.project.sq.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.sq.entity.Follow;
import com.project.sq.entity.Msg;
import com.project.sq.mapper.FollowMapper;
import com.project.sq.mapper.MsgMapper;
import com.project.sq.service.IFollowService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 关注服务实现
 *
 * @author zhuyifa
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class IFollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {

    private final FollowMapper followMapper;

    private final MsgMapper msgMapper;

    @Override
    public boolean save(Follow p) {
        // 条件构造器
        Wrapper<Follow> wrapper = new LambdaQueryWrapper<Follow>()
                // 用户ID
                .eq(Follow::getUserId, p.getUserId())
                // 关注对象ID
                .eq(Follow::getTargetId, p.getTargetId());

        // 查询单个关注
        Follow p2 = followMapper.selectOne(wrapper);

        // 操作结果
        boolean result;
        if (p2 == null) {
            // 新增关注
            result = super.save(p);
        } else {
            // 更新关注
            result = super.updateById(p2);
        }

        // 保存关注消息
        saveFollowMsg(new Msg(p));

        // 更新关注
        return result;
    }

    /**
     * 保存关注消息
     *
     * @param p 消息对象
     */
    private void saveFollowMsg(Msg p) {
        // 条件构造器
        Wrapper<Msg> wrapper = new LambdaQueryWrapper<Msg>()
                // 新粉丝
                .eq(Msg::getType, p.getType())
                // 发送人ID
                .eq(Msg::getSenderId, p.getSenderId())
                // 接收人ID
                .eq(Msg::getReceiverId, p.getReceiverId());
        // 查询单个消息
        Msg p2 = msgMapper.selectOne(wrapper);
        if (p2 == null) {
            msgMapper.insert(p);
        } else {
            msgMapper.updateById(p2);
        }
    }

    @Override
    public boolean remove(Follow p) {
        // 条件构造器
        Wrapper<Follow> wrapper = new LambdaQueryWrapper<Follow>()
                // 用户ID
                .eq(Follow::getUserId, p.getUserId())
                // 关注对象ID
                .eq(Follow::getTargetId, p.getTargetId());

        // 删除关注
        return super.remove(wrapper);
    }

}
