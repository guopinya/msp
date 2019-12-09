package com.project.msp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.msp.entity.Evaluate;
import com.project.msp.service.IEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("evaluateController")
@RequestMapping("/admin/msp/evaluate")
public class EvaluateController extends BaseController {

    @Autowired
    private IEvaluateService evaluateService;

    /**
     * 获取订单
     *
     * @param pageEntity 分页实体
     * @param evaluate   订单实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getEvaluate(PageEntity<Evaluate> pageEntity, Evaluate evaluate) {
        IPage<Evaluate> page = evaluateService.pageByEvaluateCond(pageEntity.getMpPage(), evaluate);
        return JsonResult.ok("获取订单成功", page.getTotal(), page.getRecords());
    }

    /**
     * 添加订单
     *
     * @param evaluate 订单
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addEvaluate(Evaluate evaluate) {
        evaluateService.save(evaluate);
        return JsonResult.ok("添加订单成功", evaluate);
    }

    /**
     * 修改订单
     *
     * @param evaluate 订单
     * @return 操作结果
     */
    @PutMapping
    public JsonResult modifyEvaluate(Evaluate evaluate) {
        evaluateService.updateById(evaluate);
        return JsonResult.ok("修改订单成功", evaluate);
    }

    /**
     * 删除订单
     *
     * @param evaluateId 订单ID
     * @return 操作结果
     */
    @DeleteMapping("/{evaluateId}")
    public JsonResult deleteMenu(@PathVariable String evaluateId) {
        evaluateService.removeById(evaluateId);
        return JsonResult.ok("删除订单成功", null);
    }
}

