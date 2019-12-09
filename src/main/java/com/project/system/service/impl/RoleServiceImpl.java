package com.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.system.entity.Role;
import com.project.system.mapper.RoleMapper;
import com.project.system.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色服务实现
 *
 * @author zhuyifa
 * @date 2019-10-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 按角色条件分页
     *
     * @param page 分页条件
     * @param role 角色条件
     * @return 分页对象
     */
    @Override
    public IPage<Role> pageByRoleCond(IPage<Role> page, Role role) {
        // 角色名称
        String name = role.getName();
        String isSet = role.getIsSet();

        Wrapper<Role> wrapper = new QueryWrapper<Role>().lambda()
                .like(StringUtils.isNotBlank(name), Role::getName, name)
                .eq(StringUtils.isNotBlank(isSet), Role::getIsSet, isSet);
        return roleMapper.selectPage(page, wrapper);
    }
}