package com.project.sq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.common.utils.SecurityUtils;
import com.project.sq.entity.Circle;
import com.project.sq.entity.CircleMaster;
import com.project.sq.service.ICircleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 圈子控制器
 *
 * @author zhuyifa
 */
@RestController
@RequestMapping(value = "/sq/circles")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CircleController extends BaseController {

    private final ICircleService circleService;

    /**
     * 获取圈子列表
     *
     * @param p  分页对象
     * @param p2 圈子对象
     * @return api请求结果
     */
    @GetMapping
    public JsonResult obtain(PageEntity<Circle> p, Circle p2) {
        // 查询圈子分页
        IPage<Circle> page = circleService.page(p.getMpPage(), p2);

        // 总圈子数
        long total = page.getTotal();
        // 当前圈子列表
        List<Circle> records = page.getRecords();

        return JsonResult.ok("获取圈子列表成功", total, records);
    }

    /**
     * 获取单个圈子
     *
     * @param p 圈子ID
     * @return api请求结果
     */
    @GetMapping(value = "/{p}")
    public JsonResult obtain(@PathVariable String p) {
        // 通过圈子ID查询
        Circle circle = circleService.getById(p);

        return JsonResult.ok("获取单个圈子成功", circle);
    }

    /**
     * 添加圈子
     *
     * @param p 圈子对象
     * @return api请求结果
     */
    @PostMapping
    public JsonResult addTo(Circle p) {
        // 设置默认值
        p.setDefaultValue();
        // 保存圈子
        circleService.save(p);

        return JsonResult.ok("添加圈子成功", p);
    }

    /**
     * 获取达人列表
     *
     * @param p  分页对象
     * @param p2 帖子对象
     * @return api请求结果
     */
    @GetMapping(value = "/masters")
    public JsonResult obtain(PageEntity<CircleMaster> p, CircleMaster p2) {
        // 查询圈子达人分页
        IPage<CircleMaster> page = circleService.pageMaster(p.getMpPage(), p2);

        // 圈子达人总数
        long total = page.getTotal();
        // 当前圈子达人列表
        List<CircleMaster> records = page.getRecords();

        return JsonResult.ok("获取达人列表成功", total, records);
    }

    /**
     * 加入圈子
     *
     * @param p 达人对象
     * @return api请求结果
     */
    @PostMapping(value = "/masters")
    public JsonResult addToMaster(CircleMaster p) {
        // 登录用户
        String userId = SecurityUtils.getUserId();

        // 设置达人ID（用户ID）
        p.setMasterId(userId);
        // 保存圈子
        circleService.saveMaster(p);

        return JsonResult.ok("加入圈子成功", null);
    }

    /**
     * 退出圈子
     *
     * @param p 达人对象
     * @return api请求结果
     */
    @DeleteMapping(value = "/masters")
    public JsonResult deleteMaster(CircleMaster p) {
        // 登录用户
        String userId = SecurityUtils.getUserId();

        // 设置达人ID（用户ID）
        p.setMasterId(userId);
        // 删除圈子达人
        circleService.removeMaster(p);

        return JsonResult.ok("退出圈子成功", null);
    }

}
