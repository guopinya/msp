package com.project.sq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.common.utils.SecurityUtils;
import com.project.sq.entity.Crowd;
import com.project.sq.entity.CrowdMaster;
import com.project.sq.service.ICrowdService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 众筹控制器
 *
 * @author zhuyifa
 */
@RestController
@RequestMapping(value = "/sq/crowds")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CrowdController extends BaseController {

    private final TransactionTemplate transaction;

    private final ICrowdService crowdService;

    /**
     * 获取众筹列表
     *
     * @param p  分页对象
     * @param p2 众筹对象
     * @return api请求结果
     */
    @GetMapping
    public JsonResult obtain(PageEntity<Crowd> p, Crowd p2) {
        // 查询众筹分页
        IPage<Crowd> page = crowdService.pageByEntity(p.getMpPage(), p2);

        // 众筹总数
        long total = page.getTotal();
        // 当前众筹列表
        List<Crowd> records = page.getRecords();

        return JsonResult.ok("获取众筹成功", total, records);
    }

    /**
     * 获取单个众筹
     *
     * @param p 众筹ID
     * @return api请求结果
     */
    @GetMapping(value = "/{p}")
    public JsonResult obtain(@PathVariable String p) {
        // 通过众筹ID查询
        Crowd crowd = crowdService.getById(p);

        return JsonResult.ok("获取单个众筹成功", crowd);
    }

    /**
     * 添加众筹
     *
     * @param p 众筹对象
     * @return api请求结果
     */
    @PostMapping
    public JsonResult addTo(Crowd p) {
        // 登录用户
        String userId = SecurityUtils.getUserId();

        // 设置用户ID
        p.setUserId(userId);
        // 设置默认值
        p.setDefaultValue();
        // 保存众筹
        crowdService.save(p);

        return JsonResult.ok("添加众筹成功", p);
    }

    /**
     * 获取众筹记录
     *
     * @param p  分页对象
     * @param p2 众筹对象
     * @return api请求结果
     */
    @GetMapping(value = "/masters")
    public JsonResult obtain(PageEntity<CrowdMaster> p, CrowdMaster p2) {
        // 查询众筹达人分页
        IPage<CrowdMaster> page = crowdService.pageMaster(p.getMpPage(), p2);

        // 众筹达人总数
        long total = page.getTotal();
        // 当前众筹达人列表
        List<CrowdMaster> records = page.getRecords();

        return JsonResult.ok("获取众筹记录成功", total, records);
    }

    /**
     * 购买众筹
     *
     * @param p 达人对象
     * @return api请求结果
     */
    @PostMapping(value = "/masters")
    public synchronized JsonResult addToMaster(CrowdMaster p) {
        // 登录用户
        String userId = SecurityUtils.getUserId();

        // 众筹ID
        String crowdId = p.getCrowdId();
        // 判断是否存在
        Crowd p2 = crowdService.getById(crowdId);
        if (p2 == null) {
            return JsonResult.fail("不存在众筹");
        }
        // 判断是否正在众筹
        if (!p2.inCrowdfunding()) {
            return JsonResult.fail("不在众筹中");
        }

        // 设置达人ID（用户ID）
        p.setMasterId(userId);
        // 添加已参与人数
        p2.addJoinedNum(p.getQuantity());

        // 开启事务
        transaction.execute(s -> {

            // 保存众筹达人
            crowdService.saveMaster(p);
            // 更新众筹信息
            crowdService.updateById(p2);

            return null;
        });
        return JsonResult.ok("购买众筹成功", null);
    }

}
