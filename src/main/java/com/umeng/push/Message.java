package com.umeng.push;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 消息
 *
 * @author zhuyifa
 * @since 2019-10-10
 */
public class Message {

    /**
     * 可以在ROOT级别设置的键Set
     */
    static final HashSet<String> ROOT_KEYS = new HashSet<>(Arrays.asList("appkey", "timestamp", "type", "device_tokens", "alias_type", "alias", "file_id", "filter", "payload", "policy", "production_mode", "description"));
    /**
     * 可以在POLICY级别设置的键Set
     */
    static final HashSet<String> POLICY_KEYS = new HashSet<>(Arrays.asList("start_time", "expire_time", "out_biz_no"));

    /**
     * ROOT级别Json
     */
    final JSONObject rootJson = new JSONObject();
    /**
     * PAYLOAD级别Json
     */
    final JSONObject payloadJson = new JSONObject();
    /**
     * POLICY级别Json
     */
    final JSONObject policyJson = new JSONObject();
    /**
     * 应用程序主密钥
     */
    private final String appSecret;

    /**
     * 构造函数
     *
     * @param appKey    应用唯一标识
     * @param appSecret 应用主密钥
     */
    Message(String appKey, String appSecret) {
        this.appSecret = appSecret;
        setPredefinedField("appkey", appKey);
        setPredefinedField("timestamp", Long.toString(System.currentTimeMillis()));
    }

    /**
     * 获取 请求参数
     *
     * @return 请求参数
     */
    final String getPostBody() {
        return JSON.toJSONString(rootJson);
    }

    /**
     * 获取 应用主密钥
     *
     * @return 应用主密钥
     */
    final String getAppSecret() {
        return appSecret;
    }

    /**
     * 设置 预定义字段
     *
     * @param key   键
     * @param value 值
     */
    public void setPredefinedField(String key, Object value) {
    }

    /**
     * 设置 自定义字段
     *
     * @param key   键
     * @param value 值
     */
    public void setCustomizedField(String key, Object value) {
    }
}
