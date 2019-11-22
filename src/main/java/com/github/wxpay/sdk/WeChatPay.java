package com.github.wxpay.sdk;

import com.github.wxpay.sdk.WeChatPayConstants.SignType;

import java.util.HashMap;
import java.util.Map;


/**
 * 微信支付
 *
 * @author zhuyifa
 * @since 2019-08-30
 */
public class WeChatPay {

    private WeChatPayConfig config;
    private SignType signType;
    private boolean useSandbox;
    private String notifyUrl;
    private WeChatPayRequest weChatPayRequest;

    public WeChatPay(final WeChatPayConfig config) throws Exception {
        this(config, null, false);
    }

    private WeChatPay(final WeChatPayConfig config, final String notifyUrl, final boolean useSandbox) throws Exception {
        this.config = config;
        this.notifyUrl = notifyUrl;
        this.useSandbox = useSandbox;
        // 沙箱环境
        if (useSandbox) {
            this.signType = SignType.MD5;
        } else {
            this.signType = SignType.HMACSHA256;
        }
        this.weChatPayRequest = new WeChatPayRequest(config);

        this.checkWechatPayConfig();
    }

    private void checkWechatPayConfig() throws Exception {
        if (this.config == null) {
            throw new Exception("config is null");
        }
        if (this.config.getAppId() == null || this.config.getAppId().trim().length() == 0) {
            throw new Exception("appid in config is empty");
        }
        if (this.config.getMchId() == null || this.config.getMchId().trim().length() == 0) {
            throw new Exception("appid in config is empty");
        }
        if (this.config.getWeChatPayDomain() == null) {
            throw new Exception("config.getWXPayDomain() is null");
        }

        int timeoutMs = 10;
        if (this.config.getHttpConnectTimeoutMs() < timeoutMs) {
            throw new Exception("http connect timeout is too small");
        }
        if (this.config.getHttpReadTimeoutMs() < timeoutMs) {
            throw new Exception("http read timeout is too small");
        }
    }

    /**
     * 向 Map 中添加 appid、mch_id、nonce_str、sign_type、sign <br>
     * 该函数适用于商户适用于统一下单等接口，不适用于红包、代金券接口
     *
     * @param reqData 请求数据
     * @return Map<String, String> 请求数据
     * @throws Exception 请求数据异常
     */
    private Map<String, String> fillRequestData(Map<String, String> reqData) throws Exception {
        reqData.put("appid", config.getAppId());
        reqData.put("mch_id", config.getMchId());
        reqData.put("nonce_str", WeChatPayUtil.generateNonceStr());
        if (SignType.MD5.equals(this.signType)) {
            reqData.put("sign_type", WeChatPayConstants.MD5);
        } else if (SignType.HMACSHA256.equals(this.signType)) {
            reqData.put("sign_type", WeChatPayConstants.HMACSHA256);
        }
        reqData.put("sign", WeChatPayUtil.generateSignature(reqData, config.getKey(), this.signType));
        return reqData;
    }

    /**
     * 填充APP请求数据
     *
     * @param prepayId 预支付交易会话标识
     * @return Map<String, String> 请求数据
     * @throws Exception 请求数据异常
     */
    public Map<String, String> fillAppRequestData(String prepayId) throws Exception {
        Map<String, String> reqData = new HashMap<>(16);
        // 应用ID
        reqData.put("appid", config.getAppId());
        // 商户号
        reqData.put("partnerid", config.getMchId());
        // 预支付交易会话ID
        reqData.put("prepayid", prepayId);
        // 扩展字段
        reqData.put("package", "Sign=WXPay");
        // 随机字符串
        reqData.put("noncestr", WeChatPayUtil.generateNonceStr());
        // 时间戳
        long timestamp = WeChatPayUtil.getCurrentTimestamp();
        reqData.put("timestamp", String.valueOf(timestamp));
        // 签名
        reqData.put("sign", WeChatPayUtil.generateSignature(reqData, config.getKey(), this.signType));
        return reqData;
    }

    /**
     * 填充JSAPI请求数据
     *
     * @param prepayId 预支付交易会话标识
     * @return Map<String, String> 请求数据
     * @throws Exception 请求数据异常
     */
    public Map<String, String> fillJsapiRequestData(String prepayId) throws Exception {
        Map<String, String> reqData = new HashMap<>(16);
        // 公众号id
        reqData.put("appId", config.getAppId());
        // 时间戳
        long timestamp = WeChatPayUtil.getCurrentTimestamp();
        reqData.put("timeStamp", String.valueOf(timestamp));
        // 随机字符串
        reqData.put("nonceStr", WeChatPayUtil.generateNonceStr());
        // 订单详情扩展字符串
        reqData.put("package", "prepay_id=" + prepayId);
        // 签名方式
        if (SignType.MD5.equals(this.signType)) {
            reqData.put("signType", WeChatPayConstants.MD5);
        } else if (SignType.HMACSHA256.equals(this.signType)) {
            reqData.put("signType", WeChatPayConstants.HMACSHA256);
        }
        // 签名
        reqData.put("paySign", WeChatPayUtil.generateSignature(reqData, config.getKey(), this.signType));
        return reqData;
    }

    /**
     * 判断xml数据的sign是否有效，必须包含sign字段，否则返回false。
     *
     * @param reqData 向wxpay post的请求数据
     * @return 签名是否有效
     * @throws Exception 请求数据异常
     */
    private boolean isResponseSignatureValid(Map<String, String> reqData) throws Exception {
        // 返回数据的签名方式和请求中给定的签名方式是一致的
        return WeChatPayUtil.isSignatureValid(reqData, this.config.getKey(), this.signType);
    }

    /**
     * 判断支付结果通知中的sign是否有效
     *
     * @param reqData 向wxpay post的请求数据
     * @return 签名是否有效
     * @throws Exception 请求数据异常
     */
    public boolean isPayResultNotifySignatureValid(Map<String, String> reqData) throws Exception {
        String signTypeInData = reqData.get(WeChatPayConstants.FIELD_SIGN_TYPE);
        SignType signType;
        if (signTypeInData == null) {
            signType = this.signType;
        } else {
            signTypeInData = signTypeInData.trim();
            if (signTypeInData.length() == 0) {
                signType = SignType.MD5;
            } else if (WeChatPayConstants.MD5.equals(signTypeInData)) {
                signType = SignType.MD5;
            } else if (WeChatPayConstants.HMACSHA256.equals(signTypeInData)) {
                signType = SignType.HMACSHA256;
            } else {
                throw new Exception(String.format("Unsupported sign_type: %s", signTypeInData));
            }
        }
        return WeChatPayUtil.isSignatureValid(reqData, this.config.getKey(), signType);
    }

    /**
     * 不需要证书的请求
     *
     * @param urlSuffix        String
     * @param reqData          向wxpay post的请求数据
     * @param connectTimeoutMs 超时时间，单位是毫秒
     * @param readTimeoutMs    超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception 请求异常
     */
    private String requestWithoutCert(String urlSuffix, Map<String, String> reqData,
                                      int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String nonceStr = reqData.get("nonce_str");
        String reqBody = WeChatPayUtil.mapToXml(reqData);

        return this.weChatPayRequest.requestWithoutCert(urlSuffix, nonceStr, reqBody, connectTimeoutMs, readTimeoutMs);
    }

    /**
     * 需要证书的请求
     *
     * @param urlSuffix        String
     * @param reqData          向wxpay post的请求数据  Map
     * @param connectTimeoutMs 超时时间，单位是毫秒
     * @param readTimeoutMs    超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception 请求异常
     */
    private String requestWithCert(String urlSuffix, Map<String, String> reqData,
                                   int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String nonceStr = reqData.get("nonce_str");
        String reqBody = WeChatPayUtil.mapToXml(reqData);

        return this.weChatPayRequest.requestWithCert(urlSuffix, nonceStr, reqBody, connectTimeoutMs, readTimeoutMs);
    }

    /**
     * 处理 HTTPS API返回数据，转换成Map对象。return_code为SUCCESS时，验证签名。
     *
     * @param xmlStr API返回的XML格式数据
     * @return Map类型数据
     * @throws Exception 数据异常
     */
    private Map<String, String> processResponseXml(String xmlStr) throws Exception {
        String returnCode;
        Map<String, String> respData = WeChatPayUtil.xmlToMap(xmlStr);
        if (respData.containsKey(WeChatPayConstants.RETURN_CODE)) {
            returnCode = respData.get(WeChatPayConstants.RETURN_CODE);
        } else {
            throw new Exception(String.format("No `return_code` in XML: %s", xmlStr));
        }

        if (returnCode.equals(WeChatPayConstants.FAIL)) {
            return respData;
        } else if (returnCode.equals(WeChatPayConstants.SUCCESS)) {
            if (this.isResponseSignatureValid(respData)) {
                return respData;
            } else {
                throw new Exception(String.format("Invalid sign value in XML: %s", xmlStr));
            }
        } else {
            throw new Exception(String.format("returnCode value %s is invalid in XML: %s", returnCode, xmlStr));
        }
    }

    /**
     * 作用：统一下单<br>
     * 场景：公共号支付、扫码支付、APP支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception 异常
     */
    public Map<String, String> unifiedOrder(Map<String, String> reqData) throws Exception {
        return this.unifiedOrder(reqData, config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }

    /**
     * 作用：统一下单<br>
     * 场景：公共号支付、扫码支付、APP支付
     *
     * @param reqData          向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs    读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception 异常
     */
    private Map<String, String> unifiedOrder(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WeChatPayConstants.SANDBOX_UNIFIEDORDER_URL_SUFFIX;
        } else {
            url = WeChatPayConstants.UNIFIEDORDER_URL_SUFFIX;
        }
        if (this.notifyUrl != null) {
            reqData.put("notify_url", this.notifyUrl);
        }
        String respXml = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }


    /**
     * 作用：撤销订单<br>
     * 场景：刷卡支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception 异常
     */
    public Map<String, String> reverse(Map<String, String> reqData) throws Exception {
        return this.reverse(reqData, config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：撤销订单<br>
     * 场景：刷卡支付<br>
     * 其他：需要证书
     *
     * @param reqData          向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs    读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception 异常
     */
    public Map<String, String> reverse(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WeChatPayConstants.SANDBOX_REVERSE_URL_SUFFIX;
        } else {
            url = WeChatPayConstants.REVERSE_URL_SUFFIX;
        }
        String respXml = this.requestWithCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }


    /**
     * 作用：交易保障<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception 异常
     */
    public Map<String, String> report(Map<String, String> reqData) throws Exception {
        return this.report(reqData, this.config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：交易保障<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付
     *
     * @param reqData          向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs    读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception 异常
     */
    public Map<String, String> report(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WeChatPayConstants.SANDBOX_REPORT_URL_SUFFIX;
        } else {
            url = WeChatPayConstants.REPORT_URL_SUFFIX;
        }
        String respXml = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return WeChatPayUtil.xmlToMap(respXml);
    }


    /**
     * 作用：申请退款<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付
     *
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> refund(Map<String, String> reqData) throws Exception {
        return this.refund(reqData, this.config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }

    /**
     * 作用：申请退款<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付<br>
     * 其他：需要证书
     *
     * @param reqData          向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs    读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> refund(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WeChatPayConstants.SANDBOX_REFUND_URL_SUFFIX;
        } else {
            url = WeChatPayConstants.REFUND_URL_SUFFIX;
        }
        String respXml = this.requestWithCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }
}
