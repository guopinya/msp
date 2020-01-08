package com.project.msp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.msp.entity.Search;
import com.project.msp.entity.Search;
import com.project.msp.mapper.SearchMapper;
import com.project.msp.service.ISearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl extends ServiceImpl<SearchMapper, Search> implements ISearchService {

    @Autowired
    private SearchMapper searchMapper;

    /**
     * 查询所有
     *
     * @see Wrappers#emptyWrapper()
     */
    @Override
    public List<Search> list(Wrapper<Search> search) {
        return searchMapper.list(search);
    }

    /**
     * 按条件查询分页
     *
     * @param page   分页条件
     * @param search 条件
     * @return 分页
     */
    @Override
    public IPage<Search> pageBySearchCond(IPage<Search> page, Search search) {
        return searchMapper.pageBySearchCond(page, search);
    }
}