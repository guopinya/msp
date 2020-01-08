package com.project.msp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.msp.entity.Search;

public interface ISearchService extends IService<Search> {

    /**
     * 按条件查询分页
     *
     * @param page   分页条件
     * @param search 实体条件
     * @return 分页
     */
    IPage<Search> pageBySearchCond(IPage<Search> page, Search search);
}
