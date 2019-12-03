package com.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.system.entity.Area;
import com.project.system.mapper.AreaMapper;
import com.project.system.service.IAreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 地区服务实现
 *
 * @author zhuyifa
 * @since 2019-08-08
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Autowired
    private AreaMapper areaMapper;

    /**
     * 按地区条件查询分页
     *
     * @param page 分页条件
     * @param area 地区条件
     * @return 地区分页
     */
    @Override
    public IPage<Area> pageByAreaCond(IPage<Area> page, Area area) {
        String name = area.getAreaName();
        String code = area.getAreaCode();
        String isHot = area.getIsHot();

        Wrapper<Area> wrapper = new QueryWrapper<Area>().lambda()
                .like(StringUtils.isNotBlank(name), Area::getAreaName, name)
                .like(StringUtils.isNotBlank(code), Area::getAreaCode, code)
                .eq(StringUtils.isNotBlank(isHot), Area::getIsHot, isHot);
        return areaMapper.selectPage(page, wrapper);
    }
}
