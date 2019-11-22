package com.project.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.user.entity.UserAuth;

import java.util.List;

/**
 * 用户授权服务
 *
 * @author zhuyifa
 * @since 2019-08-23
 */
public interface IUserAuthService extends IService<UserAuth> {

    /**
     * 保存用户授权信息
     *
     * @param userId     用户ID
     * @param authType   授权类型
     * @param authSecret 授权密钥
     */
    void save(String userId, String authType, String authSecret);

    /**
     * 更新用户授权信息
     *
     * @param userId     用户ID
     * @param authType   授权类型
     * @param authSecret 授权密钥
     */
    void update(String userId, String authType, String authSecret);

    /**
     * 通过用户ID查询列表
     *
     * @param userId 用户ID
     * @return 授权列表
     */
    List<UserAuth> getByUserId(String userId);

    /**
     * 通过用户ID和授权类型获取
     *
     * @param userId   用户ID
     * @param authType 授权类型
     * @return UserAuth 用户授权对象
     */
    UserAuth getByUserIdAndAuthType(String userId, String authType);

    /**
     * 通过手机号码和授权类型获取
     *
     * @param mobile   手机号码
     * @param authType 授权类型
     * @return UserAuth 用户授权对象
     */
    UserAuth getByMobileAndAuthType(String mobile, String authType);

    /**
     * 通过授权类型和授权密钥获取
     *
     * @param authType   授权类型
     * @param authSecret 授权密钥
     * @return UserAuth 用户授权对象
     */
    UserAuth getByAuthTypeAndAuthSecret(String authType, String authSecret);
}
