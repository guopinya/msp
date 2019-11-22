package com.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.system.entity.SysUser;

/**
 * 后端用户表 服务类
 *
 * @author zhuyifa
 * @since 2019-08-05
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过用户名获得
     *
     * @param username 用户名
     * @return SysUser 后台用户
     */
    SysUser getByUsername(String username);

    /**
     * 按用户条件分页
     *
     * @param page 分页条件
     * @param user 用户条件
     * @return 分页对象
     */
    IPage<SysUser> pageByUserCond(IPage<SysUser> page, SysUser user);
}
