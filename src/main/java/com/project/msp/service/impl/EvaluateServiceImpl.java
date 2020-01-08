package com.project.msp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.msp.entity.Evaluate;
import com.project.msp.mapper.EvaluateMapper;
import com.project.msp.service.IEvaluateService;
import com.project.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl extends ServiceImpl<EvaluateMapper, Evaluate> implements IEvaluateService {

    @Autowired
    private EvaluateMapper evaluateMapper;

    /**
     * 查询所有
     *
     * @see Wrappers#emptyWrapper()
     */
    @Override
    public List<Evaluate> list(Wrapper<Evaluate> evaluate) {
        return evaluateMapper.list(evaluate);
    }

    /**
     * 按条件查询分页
     *
     * @param page     分页条件
     * @param evaluate 条件
     * @return 分页
     */
    @Override
    public IPage<Evaluate> pageByEvaluateCond(IPage<Evaluate> page, Evaluate evaluate) {
        return evaluateMapper.pageByEvaluateCond(page, evaluate);
    }

}
