package com.project.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.user.entity.UserAuth;
import com.project.user.mapper.UserAuthMapper;
import com.project.user.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户授权表 服务实现类
 *
 * @author zhuyifa
 * @since 2019-08-23
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    /**
     * 保存用户授权信息
     *
     * @param userId     用户ID
     * @param authType   授权类型
     * @param authSecret 授权密钥
     */
    @Override
    public void save(String userId, String authType, String authSecret) {
        UserAuth auth = new UserAuth();
        auth.setUserId(userId);
        auth.setAuthType(authType);
        auth.setAuthSecret(authSecret);
        userAuthMapper.insert(auth);
    }

    /**
     * 更新用户授权信息
     *
     * @param userId     用户ID
     * @param authType   授权类型
     * @param authSecret 授权密钥
     */
    @Override
    public void update(String userId, String authType, String authSecret) {
        if (UserAuth.TYPE_PASSWORD.equals(authType)) {
            authSecret = UserAuth.getPassword(authSecret);
        }

        UserAuth auth = getByUserIdAndAuthType(userId, authType);
        if (auth == null) {
            save(userId, authType, authSecret);
        } else {
            userAuthMapper.update(userId, authType, authSecret);
        }
    }

    /**
     * 通过用户ID查询列表
     *
     * @param userId 用户ID
     * @return 授权列表
     */
    @Override
    public List<UserAuth> getByUserId(String userId) {
        Wrapper<UserAuth> wrapper = new QueryWrapper<UserAuth>().lambda()
                .eq(UserAuth::getUserId, userId);
        return userAuthMapper.selectList(wrapper);
    }

    /**
     * 通过用户ID和授权类型获取
     *
     * @param userId   用户ID
     * @param authType 授权类型
     * @return UserAuth 用户授权对象
     */
    @Override
    public UserAuth getByUserIdAndAuthType(String userId, String authType) {
        return userAuthMapper.selectByUserIdAndAuthType(userId, authType);
    }

    /**
     * 通过手机号码和授权类型获取
     *
     * @param mobile   手机号码
     * @param authType 授权类型
     * @return UserAuth 用户授权对象
     */
    @Override
    public UserAuth getByMobileAndAuthType(String mobile, String authType) {
        return userAuthMapper.selectByMobileAndAuthType(mobile, authType);
    }

    /**
     * 通过授权类型和授权密钥获取
     *
     * @param authType   授权类型
     * @param authSecret 授权密钥
     * @return UserAuth 用户授权对象
     */
    @Override
    public UserAuth getByAuthTypeAndAuthSecret(String authType, String authSecret) {
        return userAuthMapper.selectByAuthTypeAndAuthSecret(authType, authSecret);
    }
}
