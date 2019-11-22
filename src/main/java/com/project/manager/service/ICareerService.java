package com.project.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.manager.entity.Career;

import java.util.List;

/**
 * 职业服务
 *
 * @author tangmingxuan
 * @since 2019-07-18
 */
public interface ICareerService extends IService<Career> {

    /**
     * 获取所有职业
     *
     * @return 职业列表
     */
    List<Career> getAllCareer();

    /**
     * 通过职业条件分页
     *
     * @param page   分页条件
     * @param career 职业条件
     * @return 圈子分页
     */
    IPage<Career> pageByCareerCond(IPage<Career> page, Career career);
}