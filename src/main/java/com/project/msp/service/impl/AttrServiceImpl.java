package com.project.msp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.msp.entity.Attr;
import com.project.msp.mapper.AttrMapper;
import com.project.msp.service.IAttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements IAttrService {

    @Autowired
    private AttrMapper attrMapper;

    /**
     * 按条件查询分页
     *
     * @param page 分页条件
     * @param attr 条件
     * @return 分页
     */
    @Override
    public IPage<Attr> pageByAttrCond(IPage<Attr> page, Attr attr) {
        String attrParentId = attr.getAttrParentId();
        String attrName = attr.getAttrName();
        String attrType = attr.getAttrType();

        Wrapper<Attr> wrapper = new QueryWrapper<Attr>().lambda()
                .eq(StringUtils.isNotBlank(attrParentId), Attr::getAttrParentId, attrParentId)
                .like(StringUtils.isNotBlank(attrName), Attr::getAttrName, attrName)
                .eq(StringUtils.isNotBlank(attrType), Attr::getAttrType, attrType);
        return attrMapper.selectPage(page, wrapper);
    }
}
