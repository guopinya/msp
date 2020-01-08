package com.project.msp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.msp.entity.Project;
import com.project.msp.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("projectController")
@RequestMapping("/msp/project")
public class ProjectController extends BaseController {

    @Autowired
    private IProjectService projectService;

    /**
     * 获取项目
     *
     * @param pageEntity 分页实体
     * @param project    项目实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getProject(PageEntity<Project> pageEntity, Project project) {
        IPage<Project> page = projectService.pageByProjectCond(pageEntity.getMpPage(), project);
        return JsonResult.ok("获取项目成功", page.getTotal(), page.getRecords());
    }
}

