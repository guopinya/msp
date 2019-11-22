package com.project.stat.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 访问记录统计表
 *
 * @author tangmingxuan
 * @since 2019-10-09
 */
public class VisitRecord implements Serializable {

    /**
     * 访问类型 首页
     */
    public static final String STAT_VISIT_HOME = "HOME";
    /**
     * 访问类型 拍品首页
     */
    public static final String STAT_VISIT_AUCTION = "AUCTION";
    /**
     * 访问类型 藏品柜
     */
    public static final String STAT_VISIT_CABINET = "CABINET";
    /**
     * 访问类型 藏品详情
     */
    public static final String STAT_VISIT_CABINET_INTO = "INTO";
    /**
     * 访问类型 所有分类
     */
    public static final String STAT_VISIT_CATE_ALL = "ALL";
    /**
     * 访问类型 组装分类
     */
    public static final String STAT_VISIT_CATE_BUILD = "BUILD";
    /**
     * 访问类型 手办分类
     */
    public static final String STAT_VISIT_CATE_HAND = "HAND";
    /**
     * 访问类型 盲盒分类
     */
    public static final String STAT_VISIT_CATE_BOX = "BOX";
    /**
     * 访问类型 其他分类
     */
    public static final String STAT_VISIT_CATE_OTHER = "OTHER";
    private static final long serialVersionUID = 1L;
    /**
     * 访问类型
     */
    @TableId
    private String visitType;

    /**
     * 用户ID
     */
    @TableId
    private String userId;

    /**
     * 访问数量
     */
    private Long visitNum;


    public VisitRecord(String userId, String visitType) {
        this.userId = userId;
        this.visitType = visitType;
        this.visitNum = 1L;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public Long getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Long visitNum) {
        this.visitNum = visitNum;
    }

    public void addVisitNum() {
        this.visitNum = visitNum + 1;
    }

}
