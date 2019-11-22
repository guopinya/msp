package com.project.user.controller;


import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.gson.Gson;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.exception.BusinessException;
import com.project.user.entity.User;
import com.project.user.entity.UserAuth;
import com.project.user.service.IUserAuthService;
import com.project.user.service.IUserService;
import com.xkcoding.justauth.AuthRequestFactory;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录注册接口
 *
 * @author zhuyifa
 * @since 2019-06-12
 */
@RestController
@RequestMapping()
public class LoginController extends BaseController {

    /**
     * 手机号
     */
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^1[3456789]\\d{9}$");

    /**
     * 请求时间限制
     */
    private static final String REQUEST_TIME_LIMIT = "REQUEST_TIME_LIMIT:";
    /**
     * 账号锁定标识
     */
    private static final String ACCOUNT_LOCKOUT_LOGO = "ACCOUNT_LOCKOUT_LOGO:";
    /**
     * 密码错误次数
     */
    private static final String PASSWORD_ERROR_TIMES = "PASSWORD_ERROR_TIMES:";
    /**
     * 短信验证码
     */
    private static final String SMS_VERIFICATION_CODE = "SMS_VERIFICATION_CODE:";
    /**
     * 验证过的手机号码
     */
    private static final String VERIFIED_PHONE_NUMBER = "VERIFIED_PHONE_NUMBER:";

    private final StringRedisTemplate redisTemplate;
    private final AuthRequestFactory requestFactory;
    private final IUserAuthService userAuthService;
    private final IUserService userService;
    private final Producer producer;

    @Value("${server.domain}")
    private String serverDomain;

    public LoginController(StringRedisTemplate redisTemplate, AuthRequestFactory requestFactory, IUserAuthService userAuthService, IUserService userService, Producer producer) {
        this.redisTemplate = redisTemplate;
        this.requestFactory = requestFactory;
        this.userAuthService = userAuthService;
        this.userService = userService;
        this.producer = producer;
    }

    /**
     * 跳转登录
     *
     * @return JsonResult 操作结果
     */
    @RequestMapping("/jumpLogin")
    public JsonResult jumpLogin() {
        return JsonResult.fail(1001, "未登录/登录过期");
    }

    /**
     * 获取短信验证码
     *
     * @param mobile   手机号码
     * @param action   请求动作
     * @param authType 授权类型
     * @return JsonResult 操作结果
     */
    @PostMapping("/sendSmsCode")
    public JsonResult sendSmsCode(String mobile, String action, String authType) {
        // 检查手机号码
        inspectPhoneNumber(mobile, action, authType);

        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        // 检查短信发送间隔
        String s = opsForValue.get(REQUEST_TIME_LIMIT + mobile);
        if (s != null) {
            Long expire = redisTemplate.getExpire(REQUEST_TIME_LIMIT + mobile, TimeUnit.SECONDS);
            throw new BusinessException("发送过于频繁，请" + expire + "秒后重试");
        }

        // 验证码
        String smsCode = RandomStringUtils.randomNumeric(6);
        // 配置文件
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIg2wTWbgAUeVk", "1wXjM7CBl3lyivatq4j0VqF3QUUfze");

        // 请求对象
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "project");
        request.putQueryParameter("TemplateCode", "SMS_165412366");
        request.putQueryParameter("TemplateParam", "{\"code\": \"" + smsCode + "\"}");
        try {
            // 发送验证码
            IAcsClient client = new DefaultAcsClient(profile);
            // 响应对象
            CommonResponse resp = client.getCommonResponse(request);
            // 响应参数
            Map respMap = new Gson().fromJson(resp.getData(), Map.class);

            // 检查发送验证码是否成功
            System.out.println("resp_data = " + resp.getData());
            String returnCode = "Code";
            String mobileNumberIllegal = "isv.MOBILE_NUMBER_ILLEGAL";
            if (mobileNumberIllegal.equals(respMap.get(returnCode))) {
                return JsonResult.fail("请输入正确的手机号码");
            }
            // 检查是否发送成功
            String successCode = "OK";
            if (!successCode.equals(respMap.get(returnCode))) {
                return JsonResult.fail("发送验证码失败，请稍后再试");
            }

            // 将请求时间限制存入redis
            opsForValue.set(REQUEST_TIME_LIMIT + mobile, resp.getData(), 60, TimeUnit.SECONDS);
            // 将短信验证码存入redis
            opsForValue.set(SMS_VERIFICATION_CODE + mobile, smsCode, 10, TimeUnit.MINUTES);
            return JsonResult.ok("发送验证码成功", null);
        } catch (ClientException e) {
            throw new BusinessException("发送验证码错误", e);
        }
    }

    /**
     * 获取图形验证码
     *
     * @param request  HttpServlet请求对象
     * @param response HttpServlet响应对象
     */
    @GetMapping("/kaptcha")
    public void kaptcha(HttpServletRequest request, HttpServletResponse response) {
        // 设置标准HTTP / 1.1无缓存标头。
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        // 验证码文字
        String text = producer.createText();
        // 验证码图片
        BufferedImage image = producer.createImage(text);

        // 将验证码文字存入session
        WebUtils.setSessionAttribute(request, Constants.KAPTCHA_SESSION_KEY, text);

        try {
            // 将验证码图片输出到HttpServlet响应对象
            ServletOutputStream stream = response.getOutputStream();
            ImageIO.write(image, "jpg", stream);

            stream.flush();
            stream.close();
        } catch (IOException e) {
            throw new BusinessException("获取图形验证码错误", e);
        }
    }

    /**
     * 登录
     *
     * @param authType 授权类型
     * @param request  HttpServlet请求对象
     * @return JsonResult 操作结果
     */
    @PostMapping("/login/{authType}")
    public JsonResult login(@PathVariable String authType, HttpServletRequest request) {
        // 通过验证码登录
        if (UserAuth.TYPE_CAPTCHA.equals(authType)) {
            return loginByCaptcha(request);
        }
        // 通过密码登录
        else if (UserAuth.TYPE_PASSWORD.equals(authType)) {
            return loginByPassword(request);
        }

        // 通过社交账号登录
        try {
            String authSecret = request.getParameter("authSecret");

            // 登录动作
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(authSecret, UserAuth.DEFAULT_PASSWORD, true, authType));

            return JsonResult.ok("登录成功", subject.getPrincipal());
        } catch (AuthenticationException e) {
            throw new BusinessException("登录错误", e);
        }
    }

    /**
     * 通过验证码登录
     *
     * @param request HttpServlet请求
     * @return JsonResult 操作结果
     */

    private JsonResult loginByCaptcha(HttpServletRequest request) {
        // 检查手机号码
        String mobile = request.getParameter("mobile");
        inspectPhoneNumber(mobile, null, null);

        // 检查短信验证码
        String smsCode = request.getParameter("smsCode");
        inspectSmsCode(mobile, smsCode);

        // 登录动作
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(mobile, UserAuth.DEFAULT_PASSWORD, true, UserAuth.TYPE_CAPTCHA));

        // 删除redis短信验证码
        redisTemplate.delete(SMS_VERIFICATION_CODE + mobile);
        return JsonResult.ok("登录成功", subject.getPrincipal());
    }

    /**
     * 通过密码登录
     *
     * @param request HttpServlet请求
     * @return JsonResult 操作结果
     */

    private JsonResult loginByPassword(HttpServletRequest request) {
        // 检查手机号码
        String mobile = request.getParameter("mobile");
        inspectPhoneNumber(mobile, "password", null);

        // 检查密码是否为空
        String password = request.getParameter("password");
        if (StringUtils.isBlank(password)) {
            throw new BusinessException("密码不能为空");
        }

        try {
            // 登录动作
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(mobile, UserAuth.getPassword(password), true, UserAuth.TYPE_PASSWORD));

            return JsonResult.ok("登录成功", subject.getPrincipal());
        } catch (AuthenticationException e) {
            String s1 = redisTemplate.opsForValue().get(PASSWORD_ERROR_TIMES + mobile);
            Integer current = Integer.valueOf(StringUtils.defaultIfBlank(s1, "0"));
            int accountLockout = 5;
            if (++current == accountLockout) {
                redisTemplate.opsForValue().set(ACCOUNT_LOCKOUT_LOGO + mobile, "锁定中...", 10, TimeUnit.MINUTES);
            } else {
                redisTemplate.opsForValue().set(PASSWORD_ERROR_TIMES + mobile, String.valueOf(current), 10, TimeUnit.MINUTES);
            }

            throw new BusinessException("用户名或密码错误");
        }
    }

    /**
     * 登录
     *
     * @param authType 授权类型
     * @param response HTTP服务响应
     */
    @GetMapping("/login/{authType}")
    public void login(@PathVariable String authType, HttpServletResponse response) throws IOException {
        // 授权请求
        AuthRequest authRequest = requestFactory.get(AuthSource.valueOf(authType.toUpperCase()));
        // 授权网址
        String authorizeUrl = authRequest.authorize(authType + "::" + AuthStateUtils.createState());
        // 微信授权
        if (UserAuth.TYPE_WECHAT.equals(authType)) {
            authorizeUrl = authorizeUrl.replace("qrconnect", "oauth2/authorize").replace("snsapi_login", "snsapi_userinfo") + "#wechat_redirect";
        }
        // 重定向
        response.sendRedirect(authorizeUrl);
    }

    /**
     * 登录成功后的回调
     *
     * @param authType 第三方登录类型
     * @param callback 携带返回的信息
     */
    @GetMapping("/login/{authType}/callback")
    public void loginCallback(HttpServletRequest request, HttpServletResponse response, @PathVariable String authType, AuthCallback callback) throws IOException {
        AuthRequest authRequest = requestFactory.get(AuthSource.valueOf(authType.toUpperCase()));
        // 授权响应信息
        AuthResponse resp = authRequest.login(callback);
        System.out.println("【resp】= " + JSONUtil.toJsonStr(resp));

        // 授权用户信息
        AuthUser user = (AuthUser) resp.getData();

        try {
            String authSecret = user.getUuid();
            if (UserAuth.TYPE_WECHAT.equals(authType) || UserAuth.TYPE_QQ.equals(authType)) {
                authSecret = user.getToken().getUnionId();
            }

            // 登录动作
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(authSecret, UserAuth.DEFAULT_PASSWORD, true, authType));

            // 登录成功跳转首页
            response.sendRedirect(serverDomain);
        } catch (AuthenticationException e) {
            WebUtils.setSessionAttribute(request, "auth_redirect", user);
            response.sendRedirect(serverDomain + "/login.html?auth_type=" + authType + "#auth_redirect");
        }
    }

    /**
     * 登录绑定
     *
     * @param authType 授权类型
     * @param request  HttpServlet请求
     * @return JsonResult 操作结果
     */
    @PostMapping("/login/{authType}/binding")
    public JsonResult loginBinding(@PathVariable String authType, HttpServletRequest request) {
        // 检查手机号
        String mobile = request.getParameter("mobile");
        inspectPhoneNumber(mobile, "binding", authType);

        // 检查短信验证码
        String smsCode = request.getParameter("smsCode");
        inspectSmsCode(mobile, smsCode);

        // 用户信息
        String authSecret, avatar, username, gender;
        // 是否来自网页
        String fromWap = request.getParameter("from_wap");
        if (StringUtils.isBlank(fromWap)) {
            // 授权密钥
            authSecret = request.getParameter("authSecret");
            // 头像
            avatar = request.getParameter("avatar");
            // 用户名
            username = request.getParameter("username");
            // 性别
            gender = request.getParameter("gender");
        } else {
            // 授权用户信息
            AuthUser user = (AuthUser) WebUtils.getSessionAttribute(request, "auth_redirect");
            if (user == null) {
                return JsonResult.fail("绑定失败");
            }
            // 授权密钥
            authSecret = user.getUuid();
            if (UserAuth.TYPE_WECHAT.equals(authType) || UserAuth.TYPE_QQ.equals(authType)) {
                authSecret = user.getToken().getUnionId();
            }
            // 头像
            avatar = user.getAvatar();
            // 用户名
            username = user.getUsername();
            // 性别
            gender = user.getGender().getDesc();
        }

        // 绑定并登录
        Object userInfo = bindingAndLogin(authType, authSecret, mobile, avatar, username, gender);

        // 删除redis短信验证码
        redisTemplate.delete(SMS_VERIFICATION_CODE + mobile);
        return JsonResult.ok("绑定成功", userInfo);
    }

    /**
     * 绑定并登录
     *
     * @param authType   授权类型
     * @param authSecret 授权密钥
     * @param mobile     手机号码
     * @param avatar     头像
     * @param username   用户名
     * @param gender     性别
     * @return 用户信息
     */
    private Object bindingAndLogin(String authType, String authSecret, String mobile, String avatar, String username, String gender) {
        // 检查数据库是否存在
        User user = userService.getByMobile(mobile);
        if (user == null) {
            int maxLength = 20;
            if (username.length() > maxLength) {
                username = username.substring(0, maxLength);
            }
            // 注册用户
            user = new User(username, mobile, avatar, "1".equals(gender));
            userService.registerUser(user, UserAuth.DEFAULT_PASSWORD);
        }

        // 保存用户授权信息
        userAuthService.save(user.getUserId(), authType, authSecret);

        // 登录动作
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(authSecret, UserAuth.DEFAULT_PASSWORD, true, authType));

        return subject.getPrincipal();
    }


    /**
     * 检查用户名
     *
     * @param username 用户名
     */
    private void inspectUsername(String username) {
        // 检查用户名是否为空
        if (StringUtils.isBlank(username)) {
            throw new BusinessException("用户名不能为空");
        }

        // 检查用户名长度是否正确
        if (username.length() < 1 || username.length() > 20) {
            throw new BusinessException("用户名长度为1到20位");
        }
    }

    /**
     * 检查手机号码
     *
     * @param mobile   手机号
     * @param action   请求动作
     * @param authType 授权类型
     */
    private void inspectPhoneNumber(String mobile, String action, String authType) {
        // 检查手机号码是否为空
        if (StringUtils.isBlank(mobile)) {
            throw new BusinessException("手机号码不能为空");
        }

        // 检查手机号码是否正确
        Matcher matcher = MOBILE_PATTERN.matcher(mobile);
        if (!matcher.matches()) {
            throw new BusinessException("手机号码格式错误");
        }

        if ("password".equals(action)) {
            // 检查账号是否锁定
            String s = redisTemplate.opsForValue().get(ACCOUNT_LOCKOUT_LOGO + mobile);
            if (StringUtils.isNotBlank(s)) {
                Long minutes = redisTemplate.getExpire(ACCOUNT_LOCKOUT_LOGO + mobile, TimeUnit.MINUTES);
                if (minutes != null && minutes > 0) {
                    s = minutes + "分钟";
                } else {
                    Long seconds = redisTemplate.getExpire(ACCOUNT_LOCKOUT_LOGO + mobile, TimeUnit.SECONDS);
                    if (seconds != null && seconds > 0) {
                        s = seconds + "秒";
                    }
                }
                throw new BusinessException("账号锁定中, 请" + s + "后重试");
            }
        }

        if ("register".equals(action)) {
            // 检查手机号码是否存在
            User user = userService.getByMobile(mobile);
            if (user != null) {
                throw new BusinessException("手机号码已存在");
            }
        }

        if ("binding".equals(action)) {
            // 检查手机号码是否绑定
            UserAuth userAuth = userAuthService.getByMobileAndAuthType(mobile, authType);
            if (userAuth != null) {
                throw new BusinessException("手机号码已绑定");
            }
        }

        if (StringUtils.equals("verify", action)) {
            // 检查手机号码是否存在
            User user = userService.getByMobile(mobile);
            if (user == null) {
                throw new BusinessException("手机号码未注册");
            }
        }
    }

    /**
     * 注册
     *
     * @param request HttpServlet请求对象
     * @return JsonResult 操作结果
     */
    @PostMapping("/register")
    public JsonResult register(HttpServletRequest request) {
        // 检查用户名
        String username = request.getParameter("username");
        inspectUsername(username);

        // 检查手机号码
        String mobile = request.getParameter("mobile");
        inspectPhoneNumber(mobile, "register", null);

        // 检查短信验证码
        String smsCode = request.getParameter("smsCode");
        inspectSmsCode(mobile, smsCode);

        // 检查确认密码
        String password = request.getParameter("password");
        inspectConfirmPassword(request, password);

        // 注册用户
        User user = new User(username, mobile, User.VAL_USER_AVATAR_DEFAULT, User.VAL_USER_SEX_MAN);
        user = userService.registerUser(user, password);

        // 检查注册用户是否成功
        if (user == null) {
            throw new BusinessException("注册失败，请稍后再试");
        }

        // 登录动作
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(mobile, UserAuth.getPassword(password), true, UserAuth.TYPE_PASSWORD));

        // 删除redis短信验证码
        redisTemplate.delete(SMS_VERIFICATION_CODE + mobile);
        // 删除session图形验证码
        WebUtils.setSessionAttribute(request, Constants.KAPTCHA_SESSION_KEY, null);
        return JsonResult.ok("注册成功", subject.getPrincipal());
    }

    /**
     * 检查短信验证码
     *
     * @param mobile  手机号码
     * @param smsCode 短信验证码
     */
    private void inspectSmsCode(String mobile, String smsCode) {
        // 检查短信验证码是否为空
        if (StringUtils.isBlank(smsCode)) {
            throw new BusinessException("短信验证码不能为空");
        }

        // 检查短信验证码是否过期
        String redisCode = redisTemplate.opsForValue().get(SMS_VERIFICATION_CODE + mobile);

        // 检查短信验证码是否正确
        if (!smsCode.equals(redisCode)) {
            throw new BusinessException("短信验证码错误");
        }
    }

    /**
     * 检查确认密码
     *
     * @param request  HttpServlet请求对象
     * @param password 密码
     */
    private void inspectConfirmPassword(HttpServletRequest request, String password) {
        // 检查密码是否为空
        if (StringUtils.isBlank(password)) {
            throw new BusinessException("密码不能为空");
        }

        // 检查确认密码是否正确
        String confirmPassword = request.getParameter("confirmPassword");
        if (!password.equals(confirmPassword)) {
            throw new BusinessException("两次输入的密码不一致");
        }
    }

    /**
     * 验证手机（忘记密码第一步）
     *
     * @param request HttpServlet请求对象
     * @return JsonResult 操作结果
     */
    @PostMapping("/verifyMobile")
    public JsonResult verifyMobile(HttpServletRequest request) {
        // 检查手机号
        String mobile = request.getParameter("mobile");
        inspectPhoneNumber(mobile, "verify", null);

        // 检查短信验证码
        String smsCode = request.getParameter("smsCode");
        inspectSmsCode(mobile, smsCode);

        // 删除redis短信验证码
        redisTemplate.delete(SMS_VERIFICATION_CODE + mobile);
        // 删除session图形验证码
        WebUtils.setSessionAttribute(request, Constants.KAPTCHA_SESSION_KEY, null);
        // 将手机号码存入session中
        WebUtils.setSessionAttribute(request, VERIFIED_PHONE_NUMBER, mobile);
        return JsonResult.ok("验证手机成功", null);
    }

    /**
     * 重设密码（忘记密码第二步）
     *
     * @param request HttpServlet请求对象
     * @return JsonResult 操作结果
     */
    @PostMapping("/resetPassword")
    public JsonResult resetPassword(HttpServletRequest request) {
        // 将手机号码从session中取出
        Object mobile = WebUtils.getSessionAttribute(request, VERIFIED_PHONE_NUMBER);
        // 检查手机号码是否验证
        if (mobile == null) {
            throw new BusinessException("手机号码未验证");
        }

        // 检查确认密码
        String password = request.getParameter("password");
        inspectConfirmPassword(request, password);

        // 修改密码
        User user = userService.getByMobile((String) mobile);
        if (user == null) {
            throw new BusinessException("手机号码未注册");
        }

        userAuthService.update(user.getUserId(), UserAuth.TYPE_PASSWORD, password);
        userAuthService.update(user.getUserId(), UserAuth.TYPE_PLAINTEXT, password);

        // 登录动作
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken((String) mobile, password, true, UserAuth.TYPE_PASSWORD));

        return JsonResult.ok("重设密码成功", subject.getPrincipal());
    }

    /**
     * 注销
     *
     * @return 操作结果{{@link JsonResult}}
     */
    @PostMapping("/logout")
    public JsonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JsonResult.ok("注销成功", null);
    }
}
