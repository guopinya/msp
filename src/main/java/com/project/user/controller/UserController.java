package com.project.user.controller;

import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.exception.BusinessException;
import com.project.common.exception.InspectException;
import com.project.common.utils.SecurityUtils;
import com.project.manager.entity.Career;
import com.project.manager.service.ICareerService;
import com.project.user.entity.User;
import com.project.user.entity.UserAuth;
import com.project.user.entity.UserCareer;
import com.project.user.service.IUserAuthService;
import com.project.user.service.IUserCareerService;
import com.project.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户信息
 *
 * @author tangmingxuan
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/user/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    //    @Autowired
//    private IPostsService postsService;
    @Autowired
    private ICareerService careerService;
    //    @Autowired
//    private IFollowService followService;
    @Autowired
    private IUserAuthService userAuthService;
    @Autowired
    private IUserCareerService userCareerService;

    /**
     * 获取我的点赞和粉丝数
     *
     * @return JSON结果
     */
    @GetMapping("/getOneSocial")
    public JsonResult getOneSocial(String checkUserId) {
        String userId = User.getLoginUserId();

        Long isMySocial = JsonResult.JSON_RESULT_NO;

        if (StringUtils.isBlank(checkUserId)) {
            //说明查看的是自己的信息
            if (StringUtils.isBlank(userId)) {
                return JsonResult.fail(1001, "未登录/登录过期");
            }
            checkUserId = userId;
            isMySocial = JsonResult.JSON_RESULT_YES;
        } else {
            if (StringUtils.isNotBlank(userId)) {
                if (userId.equals(checkUserId)) {
                    isMySocial = JsonResult.JSON_RESULT_YES;
                }
            }
        }

        User user = userService.getById(checkUserId);

//        if (user != null) {
//            Integer fansSum = followService.CntMyFansNum(checkUserId);
//            Integer followsSum = followService.CntMyFollowsNum(checkUserId);
//
//            Long goodSum = postsService.getPostsGoodNumByUserId(checkUserId);
//
//            user.setFansSum(fansSum);
//            user.setFollowSum(followsSum);
//            user.setGoodSum(goodSum);
//
//            //查看是否关注了该用户
//            user.setIsFollow(followService.getPlantGrass(userId, checkUserId));
//        }

        return JsonResult.ok("获取个人信息成功", isMySocial, user);
    }

    /**
     * 修改个人信息
     *
     * @param userInfo 用户信息
     * @return JSON结果
     */
    @PostMapping("/updateUserInfo")
    public JsonResult updateUserInfo(User userInfo) {
        String userId = User.getLoginUserId();
        if (StringUtils.isBlank(userId)) {
            return JsonResult.fail(1001, "未登录/登录过期");
        }

        String username = userInfo.getUsername();

        if (userService.isDuplicateName(userId, username)) {
            return JsonResult.fail("你的用户名和他人重复，请修改");
        }

        // 1到20位
        if (username.length() < 1 || username.length() > 20) {
            throw new BusinessException("用户名长度为1到20位");
        }

        userInfo.setUserId(userId);
        userService.updateById(userInfo);
        return JsonResult.ok("修改个人信息成功");
    }

    /**
     * 更新头像
     *
     * @param avatarFile 头像文件
     * @return JSON结果
     */
    @PostMapping("/updateAvatar")
    public JsonResult updateAvatar(MultipartFile avatarFile) {
        User user = new User();
        // 设置用户ID
        user.setUserId(SecurityUtils.getUserId());
        // 设置头像图片
        user.setAvatarPath(upload(avatarFile));
        // 更新用户
        userService.updateById(user);

        return JsonResult.ok("修改头像成功");
    }

    /**
     * 更新背景图
     *
     * @param backGroundFile 背景图文件
     * @return JSON结果
     */
    @PostMapping("/updateBackGround")
    public JsonResult updateBackGround(MultipartFile backGroundFile) {
        User user = new User();
        // 设置用户ID
        user.setUserId(SecurityUtils.getUserId());
        // 设置背景图片
        user.setBackGroundPath(upload(backGroundFile));
        // 更新用户
        userService.updateById(user);

        return JsonResult.ok("修改背景图成功");
    }

    /**
     * 获取用户职业
     *
     * @param requestUserId 请求用户ID
     * @return JSON结果
     */
    @GetMapping("/getUserCareer")
    public JsonResult getUserCareer(String requestUserId) {
        String userId = User.getLoginUserId();

        if ((requestUserId == null) || StringUtils.isBlank(requestUserId)) {
            if (StringUtils.isBlank(userId)) {
                return JsonResult.fail(1001, "未登录/登录过期");
            }
            requestUserId = userId;
        }
        UserCareer userCareer = userCareerService.getById(requestUserId);

        if (userCareer != null) {
            Career career = careerService.getById(userCareer.getCareerFirstId());
            if (career != null) {
                userCareer.setCareerFirstName(career.getCareerName());
            }

            career = careerService.getById(userCareer.getCareerSecondId());
            if (career != null) {
                userCareer.setCareerSecondName(career.getCareerName());
            }

            career = careerService.getById(userCareer.getCareerThirdId());
            if (career != null) {
                userCareer.setCareerThirdName(career.getCareerName());
            }
        } else {
            return JsonResult.fail("获取用户职业异常");
        }

        return JsonResult.ok("获取用户职业成功", userCareer);
    }

    /**
     * 更新用户职业
     *
     * @param userCareer 用户职业
     * @return JSON结果
     */
    @PostMapping("/updateUserCareer")
    public JsonResult updateUserCareer(UserCareer userCareer) {
        // 设置用户ID
        userCareer.setUserId(SecurityUtils.getUserId());
        // 更新用户职业
        userCareerService.updateById(userCareer);

        return JsonResult.ok("修改用户职业成功");
    }

    /**
     * 更新密码
     *
     * @param oldPassword     原密码
     * @param newPassword     新密码
     * @param confirmPassword 确认密码
     * @return 操作结果
     */
    @PutMapping("/updatePassword")
    public JsonResult updatePassword(String oldPassword, String newPassword, String confirmPassword) {
        // 检查登录状态
        String userId = User.getLoginUserId();
        if (StringUtils.isBlank(userId)) {
            return JsonResult.fail(1001, "未登录/登录过期");
        }
        // 检查请求参数
        if (StringUtils.isAnyBlank(oldPassword, newPassword, confirmPassword)) {
            throw new InspectException("请求错误，请检查请求参数");
        }

        // 检查原密码是否正确
        String password = userService.getPasswordByUserId(userId);
        if (!oldPassword.equals(password)) {
            throw new InspectException("原密码错误 [" + oldPassword + "]");
        }

        // 检查确认密码
        if (!newPassword.equals(confirmPassword)) {
            throw new InspectException("两次输入的密码不一致");
        }

        userService.modifyPassword(userId, newPassword);
        return JsonResult.ok("修改密码成功", null);
    }

    /**
     * 获取用户授权
     *
     * @return 操作结果
     */
    @GetMapping("/auths")
    public JsonResult getAuth() {
        // 用户ID
        String userId = SecurityUtils.getUserId();

        // 用户对象
        User user = userService.getById(userId);
        // 授权列表
        List<UserAuth> authList = userAuthService.getByUserId(userId);
        // 添加手机号码授权
        authList.add(new UserAuth(userId, "mobile", user.getMobile()));

        return JsonResult.ok("获取用户授权成功", authList);
    }
}