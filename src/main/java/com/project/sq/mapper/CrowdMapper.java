package com.project.sq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.sq.entity.Circle;
import com.project.sq.entity.Crowd;
import com.project.sq.entity.Post;
import org.springframework.stereotype.Repository;

/**
 * 众筹映射器
 *
 * @author zhuyifa
 */
@Repository
public interface CrowdMapper extends BaseMapper<Crowd> {

    /**
     * 查询众筹分页
     *
     * @param p  分页对象
     * @param p2 众筹对象
     * @return 查询到的众筹分页对象
     */
    IPage<Crowd> selectPage(IPage<Crowd> p, Crowd p2);
}