package com.project.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.io.Serializable;

/**
 * 用户授权表
 *
 * @author zhuyifa
 * @since 2019-06-12
 */
public class UserAuth implements Serializable {

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";
    /**
     * 授权类型 明文密码
     */
    public static final String TYPE_PLAINTEXT = "plaintext";
    /**
     * 授权类型 加密密码
     */
    public static final String TYPE_PASSWORD = "password";
    /**
     * 授权类型 管理员
     */
    public static final String TYPE_MANAGER = "manager";
    /**
     * 授权类型 验证码
     */
    public static final String TYPE_CAPTCHA = "captcha";
    /**
     * 授权类型 微信
     */
    public static final String TYPE_WECHAT = "wechat";
    /**
     * 授权类型 QQ
     */
    public static final String TYPE_QQ = "qq";
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId
    private String userId;
    /**
     * 授权类型
     */
    private String authType;
    /**
     * 授权密钥
     */
    private String authSecret;

    public UserAuth() {
    }

    public UserAuth(String userId, String authType, String authSecret) {
        this.userId = userId;
        this.authType = authType;
        this.authSecret = authSecret;
    }

    /**
     * 获取加密密码
     *
     * @param plaintext 明文密码
     * @return 加密密码
     */
    public static String getPassword(String plaintext) {
        return new Md5Hash(plaintext, UserAuth.TYPE_PASSWORD, 3).toHex();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthSecret() {
        return authSecret;
    }

    public void setAuthSecret(String authSecret) {
        this.authSecret = authSecret;
    }
}