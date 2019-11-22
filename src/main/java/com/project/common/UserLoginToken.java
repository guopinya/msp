package com.project.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

public class UserLoginToken extends UsernamePasswordToken {

    // 序列化ID
    private static final long serialVersionUID = -2804050723838289739L;

    // 验证码
    private String captchaCode;
    /* 登录源 */
    private String loginOrigin;

    public UserLoginToken(String username, String password) {
        // 父类UsernamePasswordToken的构造函数,后两个参数暂不需要, 不设置
        super(username, password, false, "");
    }

    public UserLoginToken(String username, String password, boolean rememberMe, String host) {
        // 父类UsernamePasswordToken的构造函数,后两个参数暂不需要, 不设置
        super(username, password, rememberMe == true ? true : false, StringUtils.isEmpty(host) ? "" : host);
    }

    /**
     * 构造函数
     * 用户名和密码是登录必须的,因此构造函数中包含两个字段
     */
    public UserLoginToken(String username, String password, boolean rememberMe, String host, String captchaCode, String loginOrigin) {
        // 父类UsernamePasswordToken的构造函数,后两个参数暂不需要, 不设置
        super(username, password, rememberMe == true ? true : false, StringUtils.isEmpty(host) ? "" : host);
        this.captchaCode = captchaCode;
        this.loginOrigin = loginOrigin;
    }

    /**
     * 获取验证码
     */
    public String getCaptchaCode() {
        return captchaCode;
    }

    /**
     * 获取登录源
     */
    public String getLoginOrigin() {
        return loginOrigin;
    }
}