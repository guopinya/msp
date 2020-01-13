package com.project.sq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.msp.entity.Attr;
import com.project.sq.entity.Circle;
import com.project.sq.entity.Post;
import org.springframework.stereotype.Repository;

/**
 * 圈子映射器
 *
 * @author zhuyifa
 */
@Repository
public interface CircleMapper extends BaseMapper<Circle> {

    /**
     * 通过实体参数查询分页
     *
     * @param p  分页对象
     * @param p2 圈子对象
     * @return 查询到的圈子分页对象
     */
    IPage<Circle> selectPage(IPage<Circle> p, Circle p2);

}