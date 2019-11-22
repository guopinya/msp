package com.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.system.entity.Role;

/**
 * 角色服务
 *
 * @author zhuyifa
 * @date 2019-10-18
 */
public interface IRoleService extends IService<Role> {

    /**
     * 按角色条件分页
     *
     * @param page 分页条件
     * @param role 角色条件
     * @return 分页对象
     */
    IPage<Role> pageByRoleCond(IPage<Role> page, Role role);
}
