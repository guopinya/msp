package com.project.sq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.sq.entity.Post;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 帖子映射器
 *
 * @author zhuyifa
 */
@Repository
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 通过帖子ID查询
     *
     * @param id 帖子ID
     * @return 查询到的帖子对象
     */
    @Override
    Post selectById(Serializable id);

    /**
     * 通过用户ID查询列表
     *
     * @param userId 用户ID
     * @return 查询到的帖子列表对象
     */
    List<Post> selectList(String userId);

    /**
     * 通过实体参数查询分页
     *
     * @param p 分页对象
     * @param p2 帖子对象
     * @return 查询到的帖子分页对象
     */
    IPage<Post> selectPage(IPage<Post> p, Post p2);
}