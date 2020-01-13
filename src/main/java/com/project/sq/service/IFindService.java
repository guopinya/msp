package com.project.sq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.sq.entity.Find;

/**
 * 搜索服务
 *
 * @author zhuyifa
 */
public interface IFindService extends IService<Find> {

    /**
     * 查询搜索分页
     *
     * @param p  分页对象
     * @param p2 搜索对象
     * @return 查询到的搜索分页对象
     */
    IPage<Find> page(Page<Find> p, Find p2);
}
