package com.project.msp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.msp.entity.Attr;

public interface IAttrService extends IService<Attr> {

    /**
     * 按条件查询分页
     *
     * @param page 分页条件
     * @param attr 实体条件
     * @return 轮播图分页
     */
    IPage<Attr> pageByAttrCond(IPage<Attr> page, Attr attr);
}
