package com.project.msp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.msp.entity.Project;
import com.project.msp.mapper.ProjectMapper;
import com.project.msp.service.IProjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 按条件查询分页
     *
     * @param page    分页条件
     * @param project 条件
     * @return 分页
     */
    @Override
    public IPage<Project> pageByProjectCond(IPage<Project> page, Project project) {
        String attrId = project.getAttrId();
        String ProjectNo = project.getProjectNo();
        String ProjectName = project.getProjectName();

        Wrapper<Project> wrapper = new QueryWrapper<Project>().lambda()
                .eq(StringUtils.isNotBlank(attrId), Project::getAttrId, attrId)
                .like(StringUtils.isNotBlank(ProjectNo), Project::getProjectNo, ProjectNo)
                .like(StringUtils.isNotBlank(ProjectName), Project::getProjectName, ProjectName);
        return projectMapper.selectPage(page, wrapper);
    }
}
