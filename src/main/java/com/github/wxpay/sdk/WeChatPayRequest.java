package com.github.wxpay.sdk;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.SecureRandom;

import static com.github.wxpay.sdk.WeChatPayConstants.USER_AGENT;

/**
 * 微信支付请求
 *
 * @author zhuyifa
 * @since 2019-08-30
 */
class WeChatPayRequest {

    private WeChatPayConfig config;

    WeChatPayRequest(WeChatPayConfig config) {
        this.config = config;
    }

    /**
     * 请求，只请求一次，不做重试
     *
     * @param domain           域名
     * @param urlSuffix        网址后缀
     * @param data             请求数据
     * @param connectTimeoutMs 连接超时时间
     * @param readTimeoutMs    读取超时时间
     * @param useCert          是否使用证书，针对退款、撤销等操作
     * @return String 响应结果
     * @throws Exception 异常
     */
    private String requestOnce(final String domain, String urlSuffix, String data, int connectTimeoutMs, int readTimeoutMs, boolean useCert) throws Exception {
        BasicHttpClientConnectionManager connManager;
        if (useCert) {
            // 证书
            char[] password = config.getMchId().toCharArray();
            InputStream certStream = config.getCertStream();
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(certStream, password);

            // 实例化密钥库 & 初始化密钥工厂
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, password);

            // 创建 SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier());

            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", sslConnectionSocketFactory)
                            .build(),
                    null,
                    null,
                    null
            );
        } else {
            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", SSLConnectionSocketFactory.getSocketFactory())
                            .build(),
                    null,
                    null,
                    null
            );
        }

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();

        HttpPost httpPost = new HttpPost("https://" + domain + urlSuffix);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs).setConnectTimeout(connectTimeoutMs).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(data, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", USER_AGENT + " " + config.getMchId());
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");
    }

    /**
     * 可重试的，非双向认证的请求
     *
     * @param urlSuffix        网址后缀
     * @param data             请求数据
     * @param connectTimeoutMs 连接超时时间
     * @param readTimeoutMs    读取超时时间
     * @return String 响应结果
     * @throws Exception 异常
     */
    String requestWithoutCert(String urlSuffix, String uuid, String data, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        return this.request(urlSuffix, uuid, data, connectTimeoutMs, readTimeoutMs, false);
    }

    /**
     * 可重试的，双向认证的请求
     *
     * @param urlSuffix        网址后缀
     * @param data             请求数据
     * @param connectTimeoutMs 连接超时时间
     * @param readTimeoutMs    读取超时时间
     * @return String 响应结果
     * @throws Exception 异常
     */
    String requestWithCert(String urlSuffix, String uuid, String data, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        return this.request(urlSuffix, uuid, data, connectTimeoutMs, readTimeoutMs, true);
    }

    /**
     * 请求
     *
     * @param urlSuffix        网址后缀
     * @param data             请求数据
     * @param connectTimeoutMs 连接超时时间
     * @param readTimeoutMs    读取超时时间
     * @return String 响应结果
     * @throws Exception 异常
     */
    private String request(String urlSuffix, String uuid, String data, int connectTimeoutMs, int readTimeoutMs, boolean useCert) throws Exception {
        Exception exception;
        long elapsedTimeMillis;
        long startTimestampMs = WeChatPayUtil.getCurrentTimestampMs();
        WeChatPayDomain.DomainInfo domainInfo = config.getWeChatPayDomain().getDomain(config);
        if (domainInfo == null) {
            throw new Exception("WXPayConfig.getWXPayDomain().getDomain() is empty or null");
        }
        try {
            String result = requestOnce(domainInfo.domain, urlSuffix, data, connectTimeoutMs, readTimeoutMs, useCert);
            elapsedTimeMillis = WeChatPayUtil.getCurrentTimestampMs() - startTimestampMs;
            config.getWeChatPayDomain().report(domainInfo.domain, elapsedTimeMillis, null);

            report(domainInfo, uuid, elapsedTimeMillis, connectTimeoutMs, readTimeoutMs, false, false, false);
            return result;
        } catch (UnknownHostException ex) {  // dns 解析错误，或域名不存在
            exception = ex;
            elapsedTimeMillis = WeChatPayUtil.getCurrentTimestampMs() - startTimestampMs;
            WeChatPayUtil.getLogger().warn("UnknownHostException for domainInfo {}", domainInfo);

            report(domainInfo, uuid, elapsedTimeMillis, connectTimeoutMs, readTimeoutMs, true, false, false);
        } catch (ConnectTimeoutException ex) {
            exception = ex;
            elapsedTimeMillis = WeChatPayUtil.getCurrentTimestampMs() - startTimestampMs;
            WeChatPayUtil.getLogger().warn("connect timeout happened for domainInfo {}", domainInfo);

            report(domainInfo, uuid, elapsedTimeMillis, connectTimeoutMs, readTimeoutMs, false, true, false);
        } catch (SocketTimeoutException ex) {
            exception = ex;
            elapsedTimeMillis = WeChatPayUtil.getCurrentTimestampMs() - startTimestampMs;
            WeChatPayUtil.getLogger().warn("timeout happened for domainInfo {}", domainInfo);

            report(domainInfo, uuid, elapsedTimeMillis, connectTimeoutMs, readTimeoutMs, false, false, true);
        } catch (Exception ex) {
            exception = ex;
            elapsedTimeMillis = WeChatPayUtil.getCurrentTimestampMs() - startTimestampMs;

            report(domainInfo, uuid, elapsedTimeMillis, connectTimeoutMs, readTimeoutMs, false, false, false);
        }
        config.getWeChatPayDomain().report(domainInfo.domain, elapsedTimeMillis, exception);
        throw exception;
    }

    /**
     * 上报
     *
     * @param domainInfo                域名信息
     * @param uuid                      唯一标识
     * @param elapsedTimeMillis         运行时间，毫秒
     * @param firstConnectTimeoutMillis 连接超时时间
     * @param firstReadTimeoutMillis    读取超时时间
     * @param firstHasDnsError          是否第一次DNS错误
     * @param firstHasConnectTimeout    是否第一次连接超时
     * @param firstHasReadTimeout       是否第一次读取超时
     */
    private void report(WeChatPayDomain.DomainInfo domainInfo, String uuid, long elapsedTimeMillis, int firstConnectTimeoutMillis, int firstReadTimeoutMillis,
                        boolean firstHasDnsError, boolean firstHasConnectTimeout, boolean firstHasReadTimeout) {
        WeChatPayReport.getInstance(config).report(
                uuid,
                elapsedTimeMillis,
                domainInfo.domain,
                domainInfo.primaryDomain,
                firstConnectTimeoutMillis,
                firstReadTimeoutMillis,
                firstHasDnsError,
                firstHasConnectTimeout,
                firstHasReadTimeout
        );
    }
}
