package com.project.stat.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 发布统计表
 *
 * @author tangmingxuan
 * @since 2019-10-08
 */
public class ReleaseRecord implements Serializable {

    /**
     * 动作类型 尝试
     */
    public static final String STAT_POSTS_ACTION_ATT = "ATTEMPT";
    /**
     * 动作类型 保存草稿
     */
    public static final String STAT_POSTS_ACTION_SAVE = "SAVE";
    /**
     * 动作类型 发布
     */
    public static final String STAT_POSTS_ACTION_RELEASED = "RELEASED";
    /**
     * 动作类型 发布
     */
    public static final String STAT_POSTS_ACTION_DRAFTRELEASED = "DRAFTRELEASED";
    private static final long serialVersionUID = 1L;
    /**
     * 发布类型
     */
    @TableId
    private String postsType;

    /**
     * 尝试次数
     */
    @TableId
    private Long attempt;

    /**
     * 保存次数
     */
    private Long save;

    /**
     * 发布次数
     */
    private Long released;

    /**
     * 发布次数
     */
    private Long releaseDraft;

    public ReleaseRecord(String postsType) {
        this.postsType = postsType;
        this.attempt = 0L;
        this.save = 0L;
        this.released = 0L;
        this.releaseDraft = 0L;
    }

    public String getPostsType() {
        return postsType;
    }

    public void setPostsType(String postsType) {
        this.postsType = postsType;
    }

    public Long getSave() {
        return save;
    }

    public void setSave(Long save) {
        this.save = save;
    }

    public void addSave() {
        this.save++;
    }

    public Long getAttempt() {
        return attempt;
    }

    public void setAttempt(Long attempt) {
        this.attempt = attempt;
    }

    public void addAttempt() {
        this.attempt++;
    }

    public Long getReleased() {
        return released;
    }

    public void setReleased(Long released) {
        this.released = released;
    }

    public void addReleased() {
        this.released++;
    }

    public Long getReleaseDraft() {
        return releaseDraft;
    }

    public void setReleaseDraft(Long releaseDraft) {
        this.releaseDraft = releaseDraft;
    }

    public void addReleaseDraft() {
        this.releaseDraft++;
    }

}
