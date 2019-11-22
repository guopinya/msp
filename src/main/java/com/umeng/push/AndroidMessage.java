package com.umeng.push;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Android通知
 *
 * @author zhuyifa
 * @since 2019-10-10
 */
public class AndroidMessage extends Message {

    /**
     * 应用唯一标识
     */
    static final String APP_KEY = "5d7213690cafb201af000cec";
    /**
     * 应用主密钥
     */
    static final String APP_SECRET = "xjoe6zws6ithmpxddaltqghlfylk0jgw";
    /**
     * 可以在ROOT级别设置的键Set
     */
    private static final HashSet<String> ROOT_EXTEND_KEYS = new HashSet<>(Arrays.asList("mipush", "mi_activity"));
    /**
     * 可以在POLICY级别设置的键Set
     */
    private static final HashSet<String> POLICY_EXTEND_KEYS = new HashSet<>(Collections.singletonList("max_send_num"));
    /**
     * 可以在PAYLOAD级别设置的键Set
     */
    private static final HashSet<String> PAYLOAD_KEYS = new HashSet<>(Arrays.asList("display_type", "body", "extra"));
    /**
     * 可以在BODY级别设置的键Set
     */
    private static final HashSet<String> BODY_KEYS = new HashSet<>(Arrays.asList("ticker", "title", "text", "icon", "largeIcon", "img", "sound", "builder_id", "play_vibrate", "play_lights", "play_sound", "after_open", "url", "activity", "custom"));
    /**
     * BODY级别Json
     */
    private final JSONObject bodyJson = new JSONObject();
    /**
     * EXTRA级别Json
     */
    private final JSONObject extraJson = new JSONObject();

    /**
     * 构造函数
     */
    AndroidMessage() {
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
        if (ROOT_KEYS.contains(key) || ROOT_EXTEND_KEYS.contains(key)) {
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
        // BODY级别的键
        else if (BODY_KEYS.contains(key)) {
            bodyJson.put(key, value);

            setPredefinedField("body", bodyJson);
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
        extraJson.put(key, value);

        setPredefinedField("extra", extraJson);
    }
}
