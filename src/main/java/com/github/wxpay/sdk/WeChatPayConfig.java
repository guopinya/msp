package com.github.wxpay.sdk;

import java.io.InputStream;

/**
 * 微信支付配置
 *
 * @author zhuyifa
 * @since 2019-08-30
 */
public class WeChatPayConfig {

    private String appId;

    public WeChatPayConfig(String appId) {
        this.appId = appId;
    }

    /**
     * 获取 App ID
     *
     * @return App ID
     */
    String getAppId() {
        return appId;
    }

    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    String getMchId() {
        return "1507196971";
    }

    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    String getKey() {
        return "QNwO3mw5Lx2aqabBMUodzjHr2GC3mQLM";
    }

    /**
     * 获取商户证书内容
     *
     * @return 商户证书内容
     */
    InputStream getCertStream() {
        return getClass().getResourceAsStream("/apiclient_cert.p12");
    }

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return 连接超时时间
     */
    int getHttpConnectTimeoutMs() {
        return 6 * 1000;
    }

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return 读数据超时时间
     */
    int getHttpReadTimeoutMs() {
        return 8 * 1000;
    }

    /**
     * 获取WXPayDomain, 用于多域名容灾自动切换
     *
     * @return WXPayDomain
     */
    WeChatPayDomain getWeChatPayDomain() {
        return new WeChatPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WeChatPayConfig config) {
                return new DomainInfo(WeChatPayConstants.DOMAIN_API, true);
            }
        };
    }

    /**
     * 是否自动上报。
     * 若要关闭自动上报，子类中实现该函数返回 false 即可。
     *
     * @return 是否自动上报
     */
    boolean shouldAutoReport() {
        return true;
    }

    /**
     * 进行健康上报的线程的数量
     *
     * @return 健康上报的线程的数量
     */
    int getReportWorkerNum() {
        return 6;
    }

    /**
     * 健康上报缓存消息的最大数量。会有线程去独立上报
     * 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受
     *
     * @return 健康上报缓存消息的最大数量
     */
    int getReportQueueMaxSize() {
        return 10000;
    }

    /**
     * 批量上报，一次最多上报多个数据
     *
     * @return 批量上报数量
     */
    int getReportBatchSize() {
        return 10;
    }
}
