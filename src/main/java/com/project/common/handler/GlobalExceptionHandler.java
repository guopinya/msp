package com.project.common.handler;

import com.project.common.entity.JsonResult;
import com.project.common.exception.BusinessException;
import com.project.common.exception.InspectException;
import com.project.common.exception.LoginExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author zhuyifa
 * @since 2019-06-12
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return JsonResult.fail("不支持" + e.getMethod() + "请求");
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return JsonResult.fail("服务器错误，请联系管理员");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonResult notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return JsonResult.fail("运行时异常:" + e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public JsonResult businessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return JsonResult.fail(e.getMessage());
    }

    /**
     * 检查异常
     */
    @ExceptionHandler(InspectException.class)
    public JsonResult inspectException(InspectException e) {
        log.error(e.getMessage(), e);
        return JsonResult.fail(1002, e.getMessage());
    }

    /**
     * 登录过期异常
     */
    @ExceptionHandler(LoginExpiredException.class)
    public JsonResult inspectException(LoginExpiredException e) {
        log.error(e.getMessage(), e);
        return JsonResult.fail(1001, e.getMessage());
    }
}