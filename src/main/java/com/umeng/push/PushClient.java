package com.umeng.push;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 推送客户端
 *
 * @author zhuyifa
 * @since 2019-10-10
 */
public class PushClient {

    /**
     * 用户代理
     */
    private static final String USER_AGENT = "Mozilla/5.0";
    /**
     * 域名
     */
    private static final String DOMAIN = "http://msg.umeng.com";
    /**
     * 发送路径
     */
    private static final String SEND_PATH = "/api/send";
    /**
     * 上传路径
     */
    private static final String UPLOAD_PATH = "/upload";
    /**
     * 成功代码
     */
    private static final String OK_CODE = "SUCCESS";

    /**
     * 此对象用于向umeng发送post请求
     */
    protected HttpClient client = HttpClientBuilder.create().build();

    /**
     * 消息发送
     *
     * @param message 消息
     * @throws Exception 异常
     */
    void send(Message message) throws Exception {
        // 构造签名
        String sendUrl = DOMAIN + SEND_PATH;
        String postBody = message.getPostBody();
        String sign = DigestUtils.md5Hex("POST" + sendUrl + postBody + message.getAppSecret());

        // 构造post请求
        HttpPost post = new HttpPost(sendUrl + "?sign=" + sign);
        post.setHeader("User-Agent", USER_AGENT);

        // 设置请求参数
        StringEntity entity = new StringEntity(postBody, "UTF-8");
        post.setEntity(entity);

        // 发送post请求并获取响应
        HttpResponse response = client.execute(post);
        if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
            throw new Exception("消息发送失败");
        }
    }

    /**
     * 文件上传
     *
     * @param appKey    应用唯一标识
     * @param appSecret 应用主密钥
     * @param contents  文件内容
     * @return 文件ID
     * @throws Exception 异常
     */
    String upload(String appKey, String appSecret, String contents) throws Exception {
        // 构造json字符串
        JSONObject uploadJson = new JSONObject();
        uploadJson.put("appkey", appKey);
        uploadJson.put("content", contents);
        uploadJson.put("timestamp", Long.toString(System.currentTimeMillis()));

        // 构造签名
        String uploadUrl = DOMAIN + UPLOAD_PATH;
        String postBody = uploadJson.toString();
        String sign = DigestUtils.md5Hex("POST" + uploadUrl + postBody + appSecret);

        // 构造post请求
        HttpPost post = new HttpPost(uploadUrl + "?sign=" + sign);
        post.setHeader("User-Agent", USER_AGENT);

        // 设置请求参数
        StringEntity se = new StringEntity(postBody, "UTF-8");
        post.setEntity(se);

        // 发送post请求并获取响应
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);

        // 解码响应字符串并从中获取文件ID
        JSONObject respJson = JSONObject.parseObject(result);
        String ret = respJson.getString("ret");

        if (!OK_CODE.equals(ret)) {
            throw new Exception("文件上传失败");
        }

        JSONObject data = respJson.getJSONObject("data");
        return data.getString("file_id");
    }
}
