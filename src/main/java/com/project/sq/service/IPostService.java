package com.project.sq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.sq.entity.Post;
import com.project.sq.entity.PostMaster;

/**
 * 帖子服务
 *
 * @author zhuyifa
 */
public interface IPostService extends IService<Post> {

    /**
     * 通过实体参数分页查询
     *
     * @param p  分页对象
     * @param p2 帖子对象
     * @return 查询到的帖子分页对象
     */
    IPage<Post> page(IPage<Post> p, Post p2);

    /**
     * 查询帖子达人分页
     *
     * @param p  分页对象
     * @param p2 达人对象
     * @return 查询到的帖子达人分页
     */
    IPage<PostMaster> pageMaster(Page<PostMaster> p, PostMaster p2);

    /**
     * 保存帖子达人
     *
     * @param p 帖子达人
     */
    void saveMaster(PostMaster p);

    /**
     * 删除帖子达人
     *
     * @param p 帖子达人
     */
    void removeMaster(PostMaster p);
}
