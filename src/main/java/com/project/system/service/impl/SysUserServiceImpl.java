package com.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.system.entity.Role;
import com.project.system.entity.SysUser;
import com.project.system.mapper.SysUserMapper;
import com.project.system.service.ISysUserService;
import com.project.user.entity.User;
import com.project.user.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;

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
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 保存
     *
     * @param sysUser 实体对象
     */
    @Override
    public boolean save(SysUser sysUser) {
        transactionTemplate.execute(a -> {
            sysUser.setStatus("normal");
            sysUser.setCreateTime(LocalDateTime.now());
            boolean flag = retBool(sysUserMapper.insert(sysUser));

            String roleId = sysUser.getRoleId();
            //如果是固定身份角色
            if (flag && StringUtils.isNotBlank(roleId) && (Role.DEFAULT_ID_DISTRICT.equals(roleId)
                    || Role.DEFAULT_ID_AGENT.equals(roleId) || Role.DEFAULT_ID_FRANCHISEE.equals(roleId)
                    || Role.DEFAULT_ID_SUPERVISION.equals(roleId))) {
                User user = new User();
                user.setAvatarPath(sysUser.getAvatar());
                user.setCreateTime(sysUser.getCreateTime());
                user.setUsername(sysUser.getPhoneNumber());
                user.setMobile(sysUser.getPhoneNumber());
                user.setSignature(sysUser.getPassword());
                user.setStatus(sysUser.getStatus());
                user.setUserId(sysUser.getId());
                return retBool(userMapper.insert(user));
            }
            return flag;
        });
        return true;
    }

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
        String status = user.getStatus();
        String shopId = user.getShopId();
        String userPid0 = user.getUserPid0();
        String userPid1 = user.getUserPid1();
        String userPid2 = user.getUserPid2();

        Wrapper<SysUser> wrapper = new QueryWrapper<SysUser>().lambda()
                .eq(StringUtils.isNotBlank(roleId), SysUser::getRoleId, roleId)
                .like(StringUtils.isNotBlank(username), SysUser::getUsername, username)
                .like(StringUtils.isNotBlank(phoneNumber), SysUser::getPhoneNumber, phoneNumber)
                .eq(StringUtils.isNotBlank(status), SysUser::getStatus, status)
                .eq(StringUtils.isNotBlank(shopId), SysUser::getShopId, shopId)
                .eq(StringUtils.isNotBlank(userPid0), SysUser::getUserPid0, userPid0)
                .eq(StringUtils.isNotBlank(userPid1), SysUser::getUserPid1, userPid1)
                .eq(StringUtils.isNotBlank(userPid2), SysUser::getUserPid2, userPid2);
        return sysUserMapper.selectPage(page, wrapper);
    }
}
