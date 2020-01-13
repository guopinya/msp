package com.project.sq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.sq.entity.Post;
import com.project.sq.entity.PostMaster;
import org.springframework.stereotype.Repository;

/**
 * 帖子达人映射器
 *
 * @author zhuyifa
 */
@Repository
public interface PostMasterMapper extends BaseMapper<PostMaster> {

    /**
     * 删除帖子达人
     *
     * @param actType  操作类型 0浏览 1点赞 2评论
     * @param postId   帖子ID
     * @param masterId 达人ID（用户ID）
     * @return 是否成功
     */
    boolean delete(String actType, String postId, String masterId);

    /**
     * 查询是否存在
     *
     * @param actType  操作类型 0浏览 1点赞 2评论
     * @param postId   帖子ID
     * @param masterId 达人ID（用户ID）
     * @return 是否存在
     */
    boolean exists(String actType, String postId, String masterId);

    /**
     * 查询帖子达人分页
     *
     * @param p  分页对象
     * @param p2 达人对象
     * @return 获取到的帖子达人分页对象
     */
    IPage<PostMaster> selectPage(IPage<PostMaster> p, PostMaster p2);
}