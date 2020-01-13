package com.project.sq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.common.utils.SecurityUtils;
import com.project.sq.entity.Find;
import com.project.sq.service.IFindService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索控制器
 *
 * @author zhuyifa
 */
@RestController
@RequestMapping(value = "/sq/finds")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class FindController extends BaseController {

    private final IFindService findService;

    /**
     * 获取搜索
     *
     * @param p  分页对象
     * @param p2 帖子对象
     * @return api请求结果
     */
    @GetMapping(value = "/{opName}")
    public JsonResult obtain(PageEntity<Find> p, Find p2) {
        // 操作名称 populars热门搜索 histories搜索历史
        String opName = p2.getOpName();

        // 热门搜索
        if ("populars".equalsIgnoreCase(opName)) {
            opName = "热门搜索";
        }

        // 搜索历史
        if ("histories".equalsIgnoreCase(opName)) {
            opName = "搜索历史";
            p2.setUserId(SecurityUtils.getUserId());
        }

        // 查询搜索分页
        IPage<Find> page = findService.page(p.getMpPage(), p2);

        // 搜索总数
        long total = page.getTotal();
        // 当前搜索列表
        List<Find> records = page.getRecords();

        return JsonResult.ok(String.format("获取%s成功", opName), total, records);
    }

    /**
     * 添加搜索
     *
     * @param p 搜索对象
     * @return api请求结果
     */
    @PostMapping
    public JsonResult addTo(Find p) {
        // 登录用户
        String userId = SecurityUtils.getUserId();

        // 设置默认值
        p.setUserId(userId);
        // 保存搜索
        findService.save(p);

        return JsonResult.ok("添加搜索成功", null);
    }

}
