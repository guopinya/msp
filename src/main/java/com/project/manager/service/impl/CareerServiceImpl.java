package com.project.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.manager.entity.Career;
import com.project.manager.mapper.CareerMapper;
import com.project.manager.service.ICareerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职业服务实现
 *
 * @author tangmingxuan
 * @since 2019-07-18
 */
@Service
public class CareerServiceImpl extends ServiceImpl<CareerMapper, Career> implements ICareerService {

    @Autowired
    private CareerMapper careerMapper;

    /**
     * 获取所有职业
     *
     * @return 职业列表
     */
    @Override
    public List<Career> getAllCareer() {
        return careerMapper.selectAllCareer();
    }

    /**
     * 通过职业条件分页
     *
     * @param page   分页条件
     * @param career 职业条件
     * @return 圈子分页
     */
    @Override
    public IPage<Career> pageByCareerCond(IPage<Career> page, Career career) {
        // 职业名称
        String careerName = career.getCareerName();

        Wrapper<Career> wrapper = new QueryWrapper<Career>().lambda()
                .like(StringUtils.isNotBlank(careerName), Career::getCareerName, careerName);
        return careerMapper.selectPage(page, wrapper);
    }
}