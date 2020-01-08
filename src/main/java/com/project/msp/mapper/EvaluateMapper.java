package com.project.msp.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.msp.entity.Evaluate;
import com.project.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateMapper extends BaseMapper<Evaluate> {
    /**
     * 查询分页
     */
    List<Evaluate> list(Wrapper<Evaluate> evaluate);

    /**
     * 查询分页
     */
    IPage<Evaluate> pageByEvaluateCond(IPage<Evaluate> page, Evaluate evaluate);
}