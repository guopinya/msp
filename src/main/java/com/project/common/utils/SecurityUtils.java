package com.project.common.utils;

import com.project.common.exception.LoginExpiredException;
import com.project.system.entity.SysUser;
import com.project.user.entity.User;

/**
 * 安全工具
 *
 * @author zhuyifa
 * @since 2019-10-29
 */
public class SecurityUtils extends org.apache.shiro.SecurityUtils {

    /**
     * 获取用户ID<br/>
     * 强制登录
     *
     * @return 用户ID
     */
    public static String getUserId() {
        Object principal = getSubject().getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getUserId();
        }

        if (principal instanceof SysUser) {
            return ((SysUser) principal).getId();
        }

        throw new LoginExpiredException("未登录/登录过期");
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    public static String getUserId2() {
        Object principal = getSubject().getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getUserId();
        }

        if (principal instanceof SysUser) {
            return ((SysUser) principal).getId();
        }

        return null;
    }

}
