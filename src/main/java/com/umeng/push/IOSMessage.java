package com.umeng.push;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * IOS通知
 *
 * @author zhuyifa
 * @since 2019-10-10
 */
public class IOSMessage extends Message {

    /**
     * 应用唯一标识
     */
    static final String APP_KEY = "5da044700cafb212f20000a8";
    /**
     * 应用主密钥
     */
    static final String APP_SECRET = "fftd8epyxrvvdas2b9jxgt0enob69npl";
    /**
     * 可以在POLICY级别设置的键Set
     */
    private static final HashSet<String> POLICY_EXTEND_KEYS = new HashSet<>(Collections.singletonList("apns_collapse_id"));
    /**
     * 可以在PAYLOAD级别设置的键Set
     */
    private static final HashSet<String> PAYLOAD_KEYS = new HashSet<>(Collections.singletonList("aps"));
    /**
     * 可以在APS级别设置的键Set
     */
    private static final HashSet<String> APS_KEYS = new HashSet<>(Arrays.asList("alert", "badge", "sound", "content-available", "category"));
    /**
     * 可以在ALERT级别设置的键Set
     */
    private static final HashSet<String> ALERT_KEYS = new HashSet<>(Arrays.asList("title", "subtitle", "body"));
    /**
     * 不可以使用的键Set
     */
    private static final HashSet<String> CANNT_KEYS = new HashSet<>(Arrays.asList("d", "p"));
    /**
     * APS级别Json
     */
    private final JSONObject apsJson = new JSONObject();
    /**
     * ALERT级别Json
     */
    private final JSONObject alertJson = new JSONObject();

    /**
     * 构造函数
     */
    IOSMessage() {
        super(APP_KEY, APP_SECRET);
    }

    /**
     * 设置 预定义字段
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void setPredefinedField(String key, Object value) {
        // ROOT级别的键
        if (ROOT_KEYS.contains(key)) {
            rootJson.put(key, value);
        }
        // PAYLOAD级别的键
        else if (PAYLOAD_KEYS.contains(key)) {
            payloadJson.put(key, value);

            setPredefinedField("payload", payloadJson);
        }
        // POLICY级别的键
        else if (POLICY_KEYS.contains(key) || POLICY_EXTEND_KEYS.contains(key)) {
            policyJson.put(key, value);

            setPredefinedField("policy", payloadJson);
        }
        // APS级别的键
        else if (APS_KEYS.contains(key)) {
            apsJson.put(key, value);

            setPredefinedField("aps", apsJson);
        }
        // ALERT级别的键
        else if (ALERT_KEYS.contains(key)) {
            alertJson.put(key, value);

            setPredefinedField("alert", alertJson);
        }
        // 键不存在
        else {
            throw new RuntimeException("Unknown key: " + key);
        }
    }

    /**
     * 设置 自定义字段
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void setCustomizedField(String key, Object value) {
        if (CANNT_KEYS.contains(key)) {
            throw new RuntimeException(key + " 为友盟保留字段，不可以使用");
        }

        payloadJson.put(key, value);

        setPredefinedField("payload", payloadJson);
    }
}
