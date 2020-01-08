package com.project.msp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.msp.entity.Project;

public interface IProjectService extends IService<Project> {

    /**
     * 按条件查询分页
     *
     * @param page    分页条件
     * @param project 实体条件
     * @return 分页
     */
    IPage<Project> pageByProjectCond(IPage<Project> page, Project project);
}
