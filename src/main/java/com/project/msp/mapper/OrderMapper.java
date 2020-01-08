package com.project.msp.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.msp.entity.Evaluate;
import com.project.msp.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 查询分页
     */
    List<Order> list(Wrapper<Order> order);

    /**
     * 查询分页
     */
    IPage<Order> pageByOrderCond(IPage<Order> page, Order order);
}