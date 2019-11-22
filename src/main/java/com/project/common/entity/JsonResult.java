package com.project.common.entity;

import com.project.common.utils.HttpUtils;

import java.io.Serializable;

/**
 * Json结果
 *
 * @author zhuyifa
 * @since 2019-06-05
 */
public class JsonResult implements Serializable {

    /**
     * 消息返回值 是
     */
    public static final Long JSON_RESULT_YES = 1L;
    /**
     * 消息返回值 否
     */
    public static final Long JSON_RESULT_NO = 0L;
    private static final long serialVersionUID = 1L;
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 当前时间戳
     */
    private long timestamp;
    /**
     * 状态代码
     */
    private Integer code;
    /**
     * 状态消息
     */
    private String msg;
    /**
     * 数据总数
     */
    private Long count;
    /**
     * 数据详情
     */
    private Object data;

    private JsonResult(Integer code, String msg, Long count, Object data) {
        this.requestId = HttpUtils.getParameter("requestId");
        this.timestamp = System.currentTimeMillis();
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static JsonResult ok(Object data) {
        return ok("操作成功", data);
    }

    public static JsonResult ok(String msg, Object data) {
        return ok(msg, null, data);
    }

    public static JsonResult ok(String msg, Long count, Object data) {
        return new JsonResult(200, msg, count, data);
    }

    public static JsonResult fail(String msg) {
        return fail(400, msg);
    }

    public static JsonResult fail(String msg, Object data) {
        return new JsonResult(400, msg, null, data);
    }

    public static JsonResult fail(Integer code, String msg) {
        return new JsonResult(code, msg, null, null);
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}