package com.project.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @author zhuyifa
 * @since 2019-06-12
 */
public class User implements Serializable {

    /**
     * 用户等级 普通
     */
    public static final Integer LEVEL_USER_PEOPLE = 0;
    /**
     * 用户等级 认证
     */
    public static final Integer LEVEL_USER_CITIZEN = 1;
    /**
     * 用户等级 达人
     */
    public static final Integer LEVEL_USER_MAYOR = 2;
    /**
     * 用户头像默认地址
     */
    public static final String VAL_USER_AVATAR_DEFAULT = "/user/1141245074473861122.png";
    /**
     * 用户为男性
     */
    public static final Boolean VAL_USER_SEX_MAN = Boolean.FALSE;
    /**
     * 用户上一次发布时间
     */
    public static final String LABEL_USER_LAST_PUT_TIME = "PUTTIME";
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId
    private String userId;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private Boolean sex;

    /**
     * 签名
     */
    private String signature;

    /**
     * 据点
     */
    private String domain;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 最近登录
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recentLogin;

    /**
     * 头像路径
     */
    private String avatarPath;

    /**
     * 背景路径
     */
    private String backGroundPath;

    /**
     * 用户等级
     */
    private Integer userLevel;

    /**
     * VIP等级
     */
    private Integer vipLevel;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 融云令牌
     */
    private String ryToken;

    /**
     * 点赞总数
     */
    @TableField(exist = false)
    private Long goodSum;

    /**
     * 粉丝总数
     */
    @TableField(exist = false)
    private Integer fansSum;

    /**
     * 关注人总数
     */
    @TableField(exist = false)
    private Integer followSum;

    /**
     * 头像文件
     */
    @JsonIgnore
    @TableField(exist = false)
    private MultipartFile avatarFile;

    /**
     * 背景图片文件
     */
    @JsonIgnore
    @TableField(exist = false)
    private MultipartFile backGroundFile;

    /**
     * 是否被关注
     */
    @TableField(exist = false)
    private Boolean isFollow;

    public User() {
    }

    public User(String username, String mobile, String avatarPath, Boolean sex) {
        this.username = username;
        this.mobile = mobile;
        this.avatarPath = avatarPath;
        this.sex = sex;
        this.userLevel = 0;
        this.vipLevel = 0;
    }

    /**
     * 获取登录用户ID
     *
     * @return 登录用户ID
     */
    public static String getLoginUserId() {
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return null;
        }
        return loginUser.getUserId();
    }

    /**
     * 获取登录用户
     *
     * @return 登录用户
     */
    private static User getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return null;
        }

        Object principal = subject.getPrincipal();
        if (principal == null) {
            return null;
        }

        if (principal instanceof User) {
            return (User) principal;
        }

        return null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getRecentLogin() {
        return recentLogin;
    }

    public void setRecentLogin(LocalDateTime recentLogin) {
        this.recentLogin = recentLogin;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getBackGroundPath() {
        return backGroundPath;
    }

    public void setBackGroundPath(String backGroundPath) {
        this.backGroundPath = backGroundPath;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRyToken() {
        return ryToken;
    }

    public void setRyToken(String ryToken) {
        this.ryToken = ryToken;
    }

    public Long getGoodSum() {
        return goodSum;
    }

    public void setGoodSum(Long goodSum) {
        this.goodSum = goodSum;
    }

    public Integer getFansSum() {
        return fansSum;
    }

    public void setFansSum(Integer fansSum) {
        this.fansSum = fansSum;
    }

    public Integer getFollowSum() {
        return followSum;
    }

    public void setFollowSum(Integer followSum) {
        this.followSum = followSum;
    }

    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }

    public MultipartFile getBackGroundFile() {
        return backGroundFile;
    }

    public void setBackGroundFile(MultipartFile backGroundFile) {
        this.backGroundFile = backGroundFile;
    }

    public Boolean getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Boolean follow) {
        isFollow = follow;
    }
}