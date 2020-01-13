package com.project.sq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.utils.SecurityUtils;
import com.project.sq.entity.Circle;
import com.project.sq.entity.CircleMaster;
import com.project.sq.mapper.CircleMapper;
import com.project.sq.mapper.CircleMasterMapper;
import com.project.sq.mapper.FollowMapper;
import com.project.sq.mapper.PostMapper;
import com.project.sq.service.ICircleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * 圈子服务实现
 *
 * @author zhuyifa
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ICircleServiceImpl extends ServiceImpl<CircleMapper, Circle> implements ICircleService {

    private final CircleMapper circleMapper;
    private final CircleMasterMapper masterMapper;

    private final PostMapper postMapper;
    private final FollowMapper followMapper;

    @Override
    public Circle getById(Serializable id) {
        Circle circle = super.getById(id);

        // 登录用户  未登录则为null
        String userId = SecurityUtils.getUserId2();

        if (userId != null) {
            // 查询是否加入圈子
            circle.setIsJoined(masterMapper.exists((String) id, userId));
        }
        return circle;
    }

    @Override
    public IPage<Circle> page(Page<Circle> p, Circle p2) {
        // 是否加入
        Boolean isJoined = p2.getIsJoined();
        // 判断是否查询加入的圈子
        if (Boolean.TRUE.equals(isJoined)) {
            // 设置登录用户
            p2.setMasterId(SecurityUtils.getUserId());
        }

        return circleMapper.selectPage(p, p2);
    }

    @Override
    public IPage<CircleMaster> pageMaster(IPage<CircleMaster> p, CircleMaster p2) {
        IPage<CircleMaster> page = masterMapper.selectPage(p, p2);

        // 是否获取帖子
        Boolean isGetPost = p2.getIsGetPost();
        if (!Boolean.TRUE.equals(isGetPost)) {
            return page;
        }

        // 登录用户 未登录则为null
        String userId = SecurityUtils.getUserId2();
        // 循环查询帖子列表
        for (CircleMaster cm : page.getRecords()) {
            // 达人ID（用户ID）
            String masterId = cm.getMasterId();

            // 帖子列表
            cm.setPostList(postMapper.selectList(userId));

            // 用户为登录 默认未关注
            if (userId == null) {
                cm.setIsFollowed(Boolean.FALSE);
            } else {
                // 是否关注
                cm.setIsFollowed(followMapper.exists(userId, masterId));
            }
        }
        return page;
    }

    @Override
    public void saveMaster(CircleMaster p) {
        // 查询是否存在
        boolean exists = masterMapper.exists(
                p.getCircleId(),
                p.getMasterId());
        if (!exists) {
            masterMapper.insert(p);
        }
    }

    @Override
    public void removeMaster(CircleMaster p) {
        // 圈子ID
        String circleId = p.getCircleId();
        // 用户ID
        String masterId = p.getMasterId();

        // 删除圈子达人
        masterMapper.delete(circleId, masterId);
    }

}
