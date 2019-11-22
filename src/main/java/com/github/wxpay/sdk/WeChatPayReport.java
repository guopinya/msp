package com.github.wxpay.sdk;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import java.util.concurrent.*;

/**
 * 微信支付上报
 *
 * @author zhuyifa
 * @since 2019-08-30
 */
public class WeChatPayReport {

    private static final String REPORT_URL = "http://report.mch.weixin.qq.com/wxpay/report/default";
    private static final int DEFAULT_CONNECT_TIMEOUT_MS = 6 * 1000;
    private static final int DEFAULT_READ_TIMEOUT_MS = 8 * 1000;
    private volatile static WeChatPayReport INSTANCE;
    private LinkedBlockingQueue<String> reportMsgQueue;
    private WeChatPayConfig config;

    private WeChatPayReport(final WeChatPayConfig config) {
        this.config = config;
        reportMsgQueue = new LinkedBlockingQueue<>(config.getReportQueueMaxSize());

        // 添加处理线程
        ExecutorService executorService = Executors.newFixedThreadPool(config.getReportWorkerNum(), r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            return t;
        });

        if (config.shouldAutoReport()) {
            WeChatPayUtil.getLogger().info("report worker num: {}", config.getReportWorkerNum());
            for (int i = 0; i < config.getReportWorkerNum(); ++i) {
                executorService.execute(() -> {
                    while (true) {
                        // 先用 take 获取数据
                        try {
                            StringBuilder builder = new StringBuilder();
                            String firstMsg = reportMsgQueue.take();
                            WeChatPayUtil.getLogger().info("get first report msg: {}", firstMsg);
                            String msg;
                            builder.append(firstMsg); //会阻塞至有消息
                            int remainNum = config.getReportBatchSize() - 1;
                            for (int j = 0; j < remainNum; ++j) {
                                WeChatPayUtil.getLogger().info("try get remain report msg");
                                // msg = reportMsgQueue.poll();  // 不阻塞了
                                msg = reportMsgQueue.take();
                                WeChatPayUtil.getLogger().info("get remain report msg: {}", msg);

                                builder.append("\n");
                                builder.append(msg);
                            }
                            // 上报
                            WeChatPayReport.httpRequest(builder.toString());
                        } catch (Exception ex) {
                            WeChatPayUtil.getLogger().warn("report fail. reason: {}", ex.getMessage());
                            break;
                        }
                    }
                });
            }
        }
    }

    /**
     * 单例，双重校验，请在 JDK 1.5及更高版本中使用
     *
     * @param config 微信支付配置
     * @return WeChatPayReport 微信支付上报
     */
    static WeChatPayReport getInstance(WeChatPayConfig config) {
        if (INSTANCE == null) {
            synchronized (WeChatPayReport.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WeChatPayReport(config);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * http 请求
     *
     * @param data 请求数据
     * @throws Exception 异常
     */
    private static void httpRequest(String data) throws Exception {
        BasicHttpClientConnectionManager connManager;
        connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();

        HttpPost httpPost = new HttpPost(REPORT_URL);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(WeChatPayReport.DEFAULT_READ_TIMEOUT_MS).setConnectTimeout(WeChatPayReport.DEFAULT_CONNECT_TIMEOUT_MS).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(data, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", WeChatPayConstants.USER_AGENT);
        httpPost.setEntity(postEntity);

        httpClient.execute(httpPost);
    }

    /**
     * 上报
     *
     * @param uuid                      唯一标识
     * @param elapsedTimeMillis         运行时间，毫秒
     * @param firstDomain               域名
     * @param primaryDomain             是否主域名
     * @param firstConnectTimeoutMillis 连接超时时间
     * @param firstReadTimeoutMillis    读取超时时间
     * @param firstHasDnsError          是否第一次DNS错误
     * @param firstHasConnectTimeout    是否第一次连接超时
     * @param firstHasReadTimeout       是否第一次读取超时
     */
    public void report(String uuid, long elapsedTimeMillis,
                       String firstDomain, boolean primaryDomain, int firstConnectTimeoutMillis, int firstReadTimeoutMillis,
                       boolean firstHasDnsError, boolean firstHasConnectTimeout, boolean firstHasReadTimeout) {
        long currentTimestamp = WeChatPayUtil.getCurrentTimestamp();
        ReportInfo reportInfo = new ReportInfo(uuid, currentTimestamp, elapsedTimeMillis,
                firstDomain, primaryDomain, firstConnectTimeoutMillis, firstReadTimeoutMillis,
                firstHasDnsError, firstHasConnectTimeout, firstHasReadTimeout);
        String data = reportInfo.toLineString(config.getKey());
        WeChatPayUtil.getLogger().info("report {}", data);
        if (data != null) {
            reportMsgQueue.offer(data);
        }
    }

    public static class ReportInfo {

        /**
         * 布尔变量使用int。0为false， 1为true。
         */

        // 基本信息
        private String version = "v1";
        private String sdk = WeChatPayConstants.WXPAYSDK_VERSION;
        private String uuid;  // 交易的标识
        private long timestamp;   // 上报时的时间戳，单位秒
        private long elapsedTimeMillis; // 耗时，单位 毫秒

        // 针对主域名
        private String firstDomain;  // 第1次请求的域名
        private boolean primaryDomain; //是否主域名
        private int firstConnectTimeoutMillis;  // 第1次请求设置的连接超时时间，单位 毫秒
        private int firstReadTimeoutMillis;  // 第1次请求设置的读写超时时间，单位 毫秒
        private int firstHasDnsError;  // 第1次请求是否出现dns问题
        private int firstHasConnectTimeout; // 第1次请求是否出现连接超时
        private int firstHasReadTimeout; // 第1次请求是否出现连接超时

        ReportInfo(String uuid, long timestamp, long elapsedTimeMillis, String firstDomain, boolean primaryDomain, int firstConnectTimeoutMillis, int firstReadTimeoutMillis, boolean firstHasDnsError, boolean firstHasConnectTimeout, boolean firstHasReadTimeout) {
            this.uuid = uuid;
            this.timestamp = timestamp;
            this.elapsedTimeMillis = elapsedTimeMillis;
            this.firstDomain = firstDomain;
            this.primaryDomain = primaryDomain;
            this.firstConnectTimeoutMillis = firstConnectTimeoutMillis;
            this.firstReadTimeoutMillis = firstReadTimeoutMillis;
            this.firstHasDnsError = firstHasDnsError ? 1 : 0;
            this.firstHasConnectTimeout = firstHasConnectTimeout ? 1 : 0;
            this.firstHasReadTimeout = firstHasReadTimeout ? 1 : 0;
        }

        @Override
        public String toString() {
            return "ReportInfo{" +
                    "version='" + version + '\'' +
                    ", sdk='" + sdk + '\'' +
                    ", uuid='" + uuid + '\'' +
                    ", timestamp=" + timestamp +
                    ", elapsedTimeMillis=" + elapsedTimeMillis +
                    ", firstDomain='" + firstDomain + '\'' +
                    ", primaryDomain=" + primaryDomain +
                    ", firstConnectTimeoutMillis=" + firstConnectTimeoutMillis +
                    ", firstReadTimeoutMillis=" + firstReadTimeoutMillis +
                    ", firstHasDnsError=" + firstHasDnsError +
                    ", firstHasConnectTimeout=" + firstHasConnectTimeout +
                    ", firstHasReadTimeout=" + firstHasReadTimeout +
                    '}';
        }

        /**
         * 转换成 csv 格式
         *
         * @param key 密钥
         * @return String csv格式字符串
         */
        String toLineString(String key) {
            String separator = ",";
            Object[] objects = new Object[]{
                    version, sdk, uuid, timestamp, elapsedTimeMillis,
                    firstDomain, primaryDomain, firstConnectTimeoutMillis, firstReadTimeoutMillis,
                    firstHasDnsError, firstHasConnectTimeout, firstHasReadTimeout
            };
            StringBuilder builder = new StringBuilder();
            for (Object obj : objects) {
                builder.append(obj).append(separator);
            }
            try {
                String sign = WeChatPayUtil.hmacsha256(builder.toString(), key);
                builder.append(sign);
                return builder.toString();
            } catch (Exception ex) {
                return null;
            }
        }
    }
}
