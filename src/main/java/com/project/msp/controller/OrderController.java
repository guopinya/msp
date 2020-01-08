package com.project.msp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.common.utils.DateUtils;
import com.project.common.utils.SecurityUtils;
import com.project.common.utils.StrUtils;
import com.project.msp.entity.Order;
import com.project.msp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController("orderController")
@RequestMapping("/msp/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    /**
     * 获取订单
     *
     * @param pageEntity 分页实体
     * @param orderState 订单状态
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getOrder(PageEntity<Order> pageEntity, String orderState) {
        Order order = new Order();
        order.setOrderState(orderState);
        order.setUserId(SecurityUtils.getUserId());
        IPage<Order> page = orderService.pageByOrderCond(pageEntity.getMpPage(), order);
        return JsonResult.ok("获取订单成功", page.getTotal(), page.getRecords());
    }

    /**
     * 添加订单
     *
     * @param order 订单(代付款&当前用户)
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addOrder(Order order) {
        return orderService.create(order);
    }
}

