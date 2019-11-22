package com.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.system.entity.SysUser;
import com.project.system.mapper.SysUserMapper;
import com.project.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 端用户表 服务实现类
 *
 * @author zhuyifa
 * @since 2019-08-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 通过用户名获得
     *
     * @param username 用户名
     * @return SysUser 后台用户
     */
    @Override
    public SysUser getByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>()
                .eq("username", username);
        return sysUserMapper.selectOne(wrapper);
    }

    /**
     * 按用户条件分页
     *
     * @param page 分页条件
     * @param user 用户条件
     * @return 分页对象
     */
    @Override
    public IPage<SysUser> pageByUserCond(IPage<SysUser> page, SysUser user) {
        // 角色ID
        String roleId = user.getRoleId();
        // 用户名
        String username = user.getUsername();
        // 手机号码
        String phoneNumber = user.getPhoneNumber();

        Wrapper<SysUser> wrapper = new QueryWrapper<SysUser>().lambda()
                .eq(StringUtils.isNotBlank(roleId), SysUser::getRoleId, roleId)
                .like(StringUtils.isNotBlank(username), SysUser::getUsername, username)
                .like(StringUtils.isNotBlank(phoneNumber), SysUser::getPhoneNumber, phoneNumber);
        return sysUserMapper.selectPage(page, wrapper);
    }
}
