package com.project.stat.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 每日用户统计表
 *
 * @author tangmingxuan
 * @since 2019-09-28
 */
public class DailyUser implements Serializable {

    /**
     * 查询类型 我的
     */
    public static final String STAT_DAILY_LOGIN = "LOGIN";
    /**
     * 查询类型 所有的
     */
    public static final String STAT_DAILY_ONLINE = "ONLINE";
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId
    private String userId;

    /**
     * 创建时间
     */
    @TableId
    private String statTime;

    /**
     * 缩略图片
     */
    private String type;


    public DailyUser(String userId, String type) {
        this.userId = userId;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatTime() {
        return statTime;
    }

    public void setStatTime(String statTime) {
        this.statTime = statTime;
    }

}
