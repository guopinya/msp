package com.project.msp.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.common.entity.JsonResult;
import com.project.msp.entity.Order;

import java.util.List;

public interface IOrderService extends IService<Order> {
    /**
     * 生成订单
     *
     * @param order 实体条件
     */
    JsonResult create(Order order);

    /**
     * 查询所有
     *
     * @see Wrappers#emptyWrapper()
     */
    List<Order> list(Wrapper<Order> order);

    /**
     * 按条件查询分页
     *
     * @param page  分页条件
     * @param order 实体条件
     * @return 分页
     */
    IPage<Order> pageByOrderCond(IPage<Order> page, Order order);
}
