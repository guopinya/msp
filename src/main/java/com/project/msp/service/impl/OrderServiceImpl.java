package com.project.msp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.msp.entity.Order;
import com.project.msp.mapper.OrderMapper;
import com.project.msp.service.IOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper attrMapper;

    /**
     * 按条件查询分页
     *
     * @param page  分页条件
     * @param order 条件
     * @return 分页
     */
    @Override
    public IPage<Order> pageByOrderCond(IPage<Order> page, Order order) {
        String orderNo = order.getOrderNo();

        Wrapper<Order> wrapper = new QueryWrapper<Order>().lambda()
                .eq(StringUtils.isNotBlank(orderNo), Order::getOrderNo, orderNo);
        return attrMapper.selectPage(page, wrapper);
    }

}
