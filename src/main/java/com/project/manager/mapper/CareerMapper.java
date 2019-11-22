package com.project.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.manager.entity.Career;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Mapper 接口
 *
 * @author tangmingxuan
 * @since 2019-07-18
 */
@Repository
public interface CareerMapper extends BaseMapper<Career> {

    /**
     * 查询所有职业
     *
     * @param
     * @param
     * @return
     */
    List<Career> selectAllCareer();


}