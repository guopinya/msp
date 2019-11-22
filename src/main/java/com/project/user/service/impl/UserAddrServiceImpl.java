package com.project.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.user.entity.UserAddr;
import com.project.user.mapper.UserAddrMapper;
import com.project.user.service.IUserAddrService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户收货地址表 服务实现类
 *
 * @author zhuyifa
 * @since 2019-07-02
 */
@Service
public class UserAddrServiceImpl extends ServiceImpl<UserAddrMapper, UserAddr> implements IUserAddrService {

    @Autowired
    private UserAddrMapper userAddrMapper;

    /**
     * 通过用户ID和默认为true获取
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<UserAddr> getByUserIdAndIsDefaultTrue(String userId, Boolean isDefault) {

        return userAddrMapper.selectByUserIdAndIsDefaultTrue(userId, isDefault);
    }
}
