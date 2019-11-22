package com.project.common.config;

import com.project.system.entity.SysUser;
import com.project.system.service.ISysUserService;
import com.project.user.entity.User;
import com.project.user.entity.UserAuth;
import com.project.user.service.IUserAuthService;
import com.project.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置
 *
 * @author zhuyifa
 * @since 2019-06-12
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private IUserService userService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IUserAuthService userAuthService;

    /**
     * 领域
     *
     * @return 领域
     */
    @Bean
    public Realm realm() {
        return new UserAuthorizingRealm();
    }

    /**
     * Shiro过滤链定义
     *
     * @return ShiroFilterChainDefinition Shiro过滤链定义
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();

        // 商品模块接口
        definition.addPathDefinition("/goods/question", "user");
        definition.addPathDefinition("/goods/plantGrassOrCancel", "user");
        definition.addPathDefinition("/goods/auctions/mine", "user");

        // 订单模块接口
        definition.addPathDefinition("/order/preBidInfo", "user");
        definition.addPathDefinition("/order/confirmBidding", "user");
        definition.addPathDefinition("/order/endedPrematurely", "user");

        // 管理系统
        definition.addPathDefinition("/admin/login", "anon");
        definition.addPathDefinition("/admin/**", "user");
        return definition;
    }

    private class UserAuthorizingRealm extends AuthorizingRealm {

        /**
         * 获取授权信息
         *
         * @param principalCollection 主体集合
         * @return AuthorizationInfo 授权信息
         */
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            return null;
        }

        /**
         * 获取身份验证信息
         *
         * @param token 身份验证令牌
         * @return 身份验证信息
         * @throws AuthenticationException 身份验证异常
         */
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
            // 授权类型
            String authType = usernamePasswordToken.getHost();
            // 用户名
            String username = usernamePasswordToken.getUsername();
            if (StringUtils.isBlank(username)) {
                return null;
            }

            // 密码登录
            if (UserAuth.TYPE_PASSWORD.equals(authType)) {
                // 执行通过密码登录
                User user = userService.getByMobile(username);
                if (user == null) {
                    return null;
                }

                UserAuth userAuth = userAuthService.getByUserIdAndAuthType(user.getUserId(), authType);
                if (userAuth == null) {
                    return null;
                }

                return new SimpleAuthenticationInfo(user, userAuth.getAuthSecret(), this.getName());
            }
            // 验证码登录
            else if (UserAuth.TYPE_CAPTCHA.equals(authType)) {
                // 执行通过验证码登录
                User user = userService.getByMobile(username);
                if (user == null) {
                    // 注册用户
                    String s = username.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                    user = new User(s, username, User.VAL_USER_AVATAR_DEFAULT, User.VAL_USER_SEX_MAN);
                    userService.registerUser(user, UserAuth.DEFAULT_PASSWORD);
                }

                return new SimpleAuthenticationInfo(user, UserAuth.DEFAULT_PASSWORD, this.getName());
            }
            // 管理员登录
            else if (UserAuth.TYPE_MANAGER.equals(authType)) {
                SysUser byUsername = sysUserService.getByUsername(username);
                if (byUsername == null) {
                    return null;
                }

                return new SimpleAuthenticationInfo(byUsername, byUsername.getPassword(), this.getName());
            }

            // 社交账号登录
            UserAuth userAuth = userAuthService.getByAuthTypeAndAuthSecret(authType, username);
            if (userAuth == null) {
                return null;
            }

            User user = userService.getById(userAuth.getUserId());
            if (user == null) {
                return null;
            }

            return new SimpleAuthenticationInfo(user, UserAuth.DEFAULT_PASSWORD, this.getName());
        }
    }
}
