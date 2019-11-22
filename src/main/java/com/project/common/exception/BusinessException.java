package com.project.common.exception;

/**
 * 业务异常
 *
 * @author zhuyifa
 * @since 2019-06-10
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private Object data;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
