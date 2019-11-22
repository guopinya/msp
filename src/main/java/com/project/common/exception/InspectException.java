package com.project.common.exception;

/**
 * 检查异常
 *
 * @author zhuyifa
 * @since 2019-07-12
 */
public class InspectException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    public InspectException(String message) {
        this.message = message;
    }

    public InspectException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
