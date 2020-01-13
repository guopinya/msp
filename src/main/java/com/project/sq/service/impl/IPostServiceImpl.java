package com.project.sq.service.impl;

import static java.lang.Boolean.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.utils.SecurityUtils;
import com.project.sq.entity.Post;
import com.project.sq.entity.PostMaster;
import com.project.sq.mapper.FollowMapper;
import com.project.sq.mapper.PostMapper;
import com.project.sq.mapper.PostMasterMapper;
import com.project.sq.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * 帖子服务实现
 *
 * @author zhuyifa
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class IPostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    private final PostMapper postMapper;
    private final FollowMapper followMapper;
    private final PostMasterMapper masterMapper;

    /**
     * 设置扩展字段
     *
     * @param post       帖子对象
     * @param isLiked    是否点赞
     * @param isFollowed 是否关注
     */
    private void setExtendField(Post post, Boolean isLiked, Boolean isFollowed) {
        // 登录用户 未登录则为null
        String userId = SecurityUtils.getUserId2();

        // 用户未登录 则默认为未点赞/未关注
        post.setIsLiked(userId != null);
        post.setIsFollowed(userId != null);

        // 用户已登录 则需判断是否要重新设值
        if (userId != null) {
            // 不是查询已点赞的帖子
            if (!TRUE.equals(isLiked)) {
                // 重新设置是否点赞
                post.setIsLiked(masterMapper.exists("0", post.getId(), userId));
            }
            // 不是查询已关注的帖子
            if (!TRUE.equals(isFollowed)) {
                // 重新设置是否关注
                post.setIsFollowed(followMapper.exists(userId, post.getUserId()));
            }
        }
    }

    @Override
    public Post getById(Serializable id) {
        // 查询单个帖子
        Post post = postMapper.selectById(id);
        // 设置扩展字段
        setExtendField(post, false, false);

        return post;
    }

    @Override
    public IPage<Post> page(IPage<Post> p, Post p2) {
        // 是否点赞
        Boolean isLiked = p2.getIsLiked();
        // 是否关注
        Boolean isFollowed = p2.getIsFollowed();
        // 查询已点赞的帖子 或 查询已关注的帖子
        if (TRUE.equals(isLiked) || TRUE.equals(isFollowed)) {
            // 设置用户ID 需要用户登录
            p2.setUserId(SecurityUtils.getUserId());
        }

        // 通过实体参数查询分页
        postMapper.selectPage(p, p2);
        // 循环判断是否点赞&关注
        for (Post s : p.getRecords()) {
            // 设置扩展字段
            setExtendField(s, isLiked, isFollowed);
        }
        return p;
    }

    @Override
    public IPage<PostMaster> pageMaster(Page<PostMaster> p, PostMaster p2) {
        // 查询帖子达人分页
        return masterMapper.selectPage(p, p2);
    }

    @Override
    public void saveMaster(PostMaster p) {
        // 操作类型 0浏览 1点赞 2评论
        String type = p.getActType();
        // 评论直接保存
        if ("2".equalsIgnoreCase(type)) {
            masterMapper.insert(p);
            return;
        }

        // 帖子ID
        String postId = p.getPostId();
        // 用户ID
        String masterId = p.getMasterId();
        // 浏览/点赞不存在才保存
        if (!masterMapper.exists(type, postId, masterId)) {
            // 保存帖子达人
            masterMapper.insert(p);
        }
    }

    @Override
    public void removeMaster(PostMaster p) {
        // 操作类型 0浏览 1点赞 2评论
        String type = p.getActType();
        // 帖子ID
        String postId = p.getPostId();
        // 用户ID
        String masterId = p.getMasterId();

        // 删除帖子达人
        masterMapper.delete(type, postId, masterId);
    }

}
