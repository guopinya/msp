package com.project.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.user.entity.UserAuth;
import org.springframework.stereotype.Repository;

/**
 * 用户授权表 Mapper 接口
 *
 * @author zhuyifa
 * @since 2019-06-12
 */
@Repository
public interface UserAuthMapper extends BaseMapper<UserAuth> {

    /**
     * 通过授权类型和授权密钥选择
     *
     * @param authType   授权类型
     * @param authSecret 授权密钥
     * @return UserAuth 用户授权对象
     */
    UserAuth selectByAuthTypeAndAuthSecret(String authType, String authSecret);

    /**
     * 通过手机号码和授权类型选择
     *
     * @param mobile   手机号码
     * @param authType 授权类型
     * @return UserAuth 用户授权对象
     */
    UserAuth selectByMobileAndAuthType(String mobile, String authType);

    /**
     * 通过用户ID和授权类型选择
     *
     * @param userId   用户ID
     * @param authType 授权类型
     * @return UserAuth 用户授权对象
     */
    UserAuth selectByUserIdAndAuthType(String userId, String authType);

    /**
     * 更新用户授权信息
     *
     * @param userId     用户ID
     * @param authType   授权类型
     * @param authSecret 授权密钥
     */
    void update(String userId, String authType, String authSecret);
}
