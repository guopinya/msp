package com.github.wxpay.sdk;


/**
 * 微信支付域名管理，实现主备域名自动切换
 *
 * @author zhuyifa
 * @since 2019-08-30
 */
public interface WeChatPayDomain {

    /**
     * 上报域名网络状况
     *
     * @param domain            域名。 比如：api.mch.weixin.qq.com
     * @param elapsedTimeMillis 耗时
     * @param ex                网络请求中出现的异常。
     *                          null表示没有异常
     *                          ConnectTimeoutException，表示建立网络连接异常
     *                          UnknownHostException， 表示dns解析异常
     */
    void report(final String domain, long elapsedTimeMillis, final Exception ex);

    /**
     * 获取域名
     *
     * @param config 配置
     * @return 域名
     */
    DomainInfo getDomain(final WeChatPayConfig config);

    class DomainInfo {
        // 域名
        public String domain;
        // 该域名是否为主域名。例如:api.mch.weixin.qq.com为主域名
        boolean primaryDomain;

        DomainInfo(String domain, boolean primaryDomain) {
            this.domain = domain;
            this.primaryDomain = primaryDomain;
        }

        @Override
        public String toString() {
            return "DomainInfo{" +
                    "domain='" + domain + '\'' +
                    ", primaryDomain=" + primaryDomain +
                    '}';
        }
    }
}