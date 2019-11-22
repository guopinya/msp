package com.project.common.exception;

/**
 * 登录过期异常
 *
 * @author zhuyifa
 * @since 2019-10-29
 */
public class LoginExpiredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    public LoginExpiredException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
