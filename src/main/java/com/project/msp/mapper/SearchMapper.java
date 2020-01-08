package com.project.msp.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.msp.entity.Search;
import com.project.msp.entity.Search;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchMapper extends BaseMapper<Search> {
    /**
     * 查询分页
     */
    List<Search> list(Wrapper<Search> search);

    /**
     * 查询分页
     */
    IPage<Search> pageBySearchCond(IPage<Search> page, Search search);
}