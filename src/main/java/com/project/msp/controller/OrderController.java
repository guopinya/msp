package com.project.msp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.msp.entity.Order;
import com.project.msp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController("orderController")
@RequestMapping("/admin/msp/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    /**
     * 获取订单
     *
     * @param pageEntity 分页实体
     * @param order      订单实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getOrder(PageEntity<Order> pageEntity, Order order) {
        IPage<Order> page = orderService.pageByOrderCond(pageEntity.getMpPage(), order);
        return JsonResult.ok("获取订单成功", page.getTotal(), page.getRecords());
    }

    /**
     * 添加订单
     *
     * @param order 订单
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addOrder(Order order) {
        orderService.save(order);
        return JsonResult.ok("添加订单成功", order);
    }

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 操作结果
     */
    @PutMapping
    public JsonResult modifyOrder(Order order) {
        orderService.updateById(order);
        return JsonResult.ok("修改订单成功", order);
    }

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 操作结果
     */
    @PutMapping("preTime")
    public JsonResult modifyOrder_preTime(Order order) {
        Date preTime = order.getPreTime();
        order = orderService.getById(order.getId());
        order.setPreTime(preTime);
        orderService.updateById(order);
        return JsonResult.ok("修改订单成功", order);
    }

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 操作结果
     */
    @PutMapping("servicer")
    public JsonResult modifyOrder_servicer(Order order) {
        String servicerId = order.getServicerId();
        order = orderService.getById(order.getId());
        order.setSuccTime(new Date());
        order.setState("30");
        order.setServicerId(servicerId);
        orderService.updateById(order);
        return JsonResult.ok("修改订单成功", order);
    }

    /**
     * 删除订单
     *
     * @param orderId 订单ID
     * @return 操作结果
     */
    @DeleteMapping("/{orderId}")
    public JsonResult deleteMenu(@PathVariable String orderId) {
        orderService.removeById(orderId);
        return JsonResult.ok("删除订单成功", null);
    }
}

