package com.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.system.entity.Area;

import java.util.List;

/**
 * 地区服务
 *
 * @author zhuyifa
 * @since 2019-08-08
 */
public interface IAreaService extends IService<Area> {

    /**
     * 按地区条件查询分页
     *
     * @param area 地区条件
     * @return 地区分页
     */
    List<Area> list(Area area);

    /**
     * 按地区条件查询分页
     *
     * @param page 分页条件
     * @param area 地区条件
     * @return 地区分页
     */
    IPage<Area> pageByAreaCond(IPage<Area> page, Area area);
}
