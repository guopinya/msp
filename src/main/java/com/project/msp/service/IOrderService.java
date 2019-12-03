package com.project.msp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.msp.entity.Order;

public interface IOrderService extends IService<Order> {

    /**
     * 按条件查询分页
     *
     * @param page  分页条件
     * @param order 实体条件
     * @return 轮播图分页
     */
    IPage<Order> pageByOrderCond(IPage<Order> page, Order order);
}
