package com.project.admin;

import com.google.code.kaptcha.Constants;
import com.project.common.UserLoginToken;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.user.entity.UserAuth;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 入口控制器
 *
 * @author zhuyifa
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/admin")
public class EntranceController extends BaseController {

    @PostMapping("/login")
    public JsonResult login(HttpServletRequest request, String username, String password, String vercode, String remember) {
        Object obj = WebUtils.getSessionAttribute(request, Constants.KAPTCHA_SESSION_KEY);
        String code = String.valueOf(obj != null ? obj : "");
        WebUtils.setSessionAttribute(request, Constants.KAPTCHA_SESSION_KEY, null);
        if (StringUtils.isEmpty(vercode) || !vercode.equalsIgnoreCase(code)) {
            return JsonResult.fail(4001, "验证码错误");
        }

        UserLoginToken token = new UserLoginToken(username, password);
        token.setHost(UserAuth.TYPE_MANAGER);
        token.setRememberMe(Boolean.parseBoolean(remember));

        Subject subject = SecurityUtils.getSubject();
        try {
            // 登录
            subject.login(token);
        } catch (Exception e) {
            return JsonResult.fail("用户名或密码错误");
        }

        return JsonResult.ok("登录成功", subject.getPrincipal());
    }

    @GetMapping("/session")
    public JsonResult session() {
        Subject subject = SecurityUtils.getSubject();
        return JsonResult.ok("登录成功", subject.getPrincipal());
    }

    @PostMapping("/upload")
    public JsonResult upload(MultipartFile file) {
        return JsonResult.ok("上传成功", super.upload(file));
    }
}
