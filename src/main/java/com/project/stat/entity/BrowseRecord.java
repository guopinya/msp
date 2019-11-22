package com.project.stat.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 浏览记录统计表
 *
 * @author tangmingxuan
 * @since 2019-09-28
 */
public class BrowseRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子ID
     */
    @TableId
    private String postId;

    /**
     * 用户ID
     */
    @TableId
    private String userId;

    /**
     * 帖子类型
     */
    private String postType;

    /**
     * 浏览数量
     */
    private Integer browseNum;


    public BrowseRecord(String userId, String postId, String postType) {
        this.userId = userId;
        this.postId = postId;
        this.postType = postType;
        this.browseNum = 1;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public void addBrowseNum() {
        this.browseNum = browseNum + 1;
    }

}
