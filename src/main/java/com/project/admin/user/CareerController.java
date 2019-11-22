package com.project.admin.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.manager.entity.Career;
import com.project.manager.service.ICareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 身份控制器
 *
 * @author zhuyifa
 * @since 2019-08-09
 */
@RestController("careerController2")
@RequestMapping("/admin/user/careers")
public class CareerController extends BaseController {

    @Autowired
    private ICareerService careerService;

    /**
     * 获取身份
     *
     * @param pageEntity 分页实体
     * @param career     身份实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getCareer(PageEntity<Career> pageEntity, Career career) {
        IPage<Career> page = careerService.pageByCareerCond(pageEntity.getMpPage(), career);
        return JsonResult.ok("获取身份成功", page.getTotal(), page.getRecords());
    }

    /**
     * 添加身份
     *
     * @param career 身份
     * @return JSON结果
     */
    @PostMapping
    public JsonResult addCareer(Career career) {
        careerService.save(career);
        return JsonResult.ok("添加身份成功", career);
    }

    /**
     * 修改身份
     *
     * @param career 身份
     * @return JSON结果
     */
    @PutMapping
    public JsonResult getCareer(Career career) {
        careerService.updateById(career);
        return JsonResult.ok("修改身份成功", career);
    }
}
