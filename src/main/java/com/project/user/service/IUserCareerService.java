package com.project.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.user.entity.UserCareer;

/**
 * 用户职业表 服务类
 *
 * @author tangmingxuan
 * @since 2019-08-02
 */
public interface IUserCareerService extends IService<UserCareer> {

    /**
     * 通过用户ID获取
     *
     * @param userId 用户ID
     * @return 用户职业
     */
    UserCareer getByUserId(String userId);
}