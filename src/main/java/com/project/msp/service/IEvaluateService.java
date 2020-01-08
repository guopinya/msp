package com.project.msp.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.msp.entity.Evaluate;

import java.util.List;

public interface IEvaluateService extends IService<Evaluate> {
    /**
     * 查询所有
     *
     * @see Wrappers#emptyWrapper()
     */
    List<Evaluate> list(Wrapper<Evaluate> evaluate);

    /**
     * 按条件查询分页
     *
     * @param page     分页条件
     * @param evaluate 实体条件
     * @return 分页
     */
    IPage<Evaluate> pageByEvaluateCond(IPage<Evaluate> page, Evaluate evaluate);
}
