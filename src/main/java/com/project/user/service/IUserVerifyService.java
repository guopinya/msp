package com.project.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.user.entity.UserVerify;

/**
 * 用户认证服务
 *
 * @author zhuyifa
 * @since 2019-10-31
 */
public interface IUserVerifyService extends IService<UserVerify> {

    /**
     * 按认证条件分页
     *
     * @param page   分页条件
     * @param verify 认证条件
     * @return 用户认证分页
     */
    IPage<UserVerify> pageByVerifyCond(IPage<UserVerify> page, UserVerify verify);
}