package com.project.admin.msp;

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

@RestController("projectController_admin")
@RequestMapping("/admin/msp/project")
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

    /**
     * 添加项目
     *
     * @param project   项目
     * @param imageFile 图片文件
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addProject(Project project, MultipartFile imageFile, List<MultipartFile> bannerFiles) {
        project.setProjectImage(upload(imageFile));
        project.setProjectBanner(upload(bannerFiles));
        projectService.save(project);
        return JsonResult.ok("添加项目成功", project);
    }

    /**
     * 修改项目
     *
     * @param project   项目
     * @param imageFile 图片文件
     * @return 操作结果
     */
    @PutMapping
    public JsonResult modifyProject(Project project, MultipartFile imageFile, List<MultipartFile> bannerFiles) {
        project.setProjectImage(upload(imageFile));
        project.setProjectBanner(assembleFiles(upload(bannerFiles), project.getProjectBanner()));
        projectService.updateById(project);
        return JsonResult.ok("修改项目成功", project);
    }

    /**
     * 删除项目
     *
     * @param projectId 项目ID
     * @return 操作结果
     */
    @DeleteMapping("/{projectId}")
    public JsonResult deleteMenu(@PathVariable String projectId) {
        projectService.removeById(projectId);
        return JsonResult.ok("删除项目成功", null);
    }
}

