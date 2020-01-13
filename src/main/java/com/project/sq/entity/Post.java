package com.project.sq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

/**
 * 帖子
 *
 * @author zhuyifa
 */
@Data
@TableName(value = "sq_post")
public class Post extends BaseEnity {

    /**
     * 帖子ID
     */
    @TableId
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 话题ID
     */
    private String topicId;
    /**
     * 圈子ID
     */
    private String circleId;
    /**
     * 帖子图片
     */
    private String image;
    /**
     * 帖子标题
     */
    private String title;
    /**
     * 帖子内容
     */
    private String content;
    /**
     * 点赞数量
     */
    private Integer likeNum;
    /**
     * 浏览数量
     */
    private Integer browseNum;
    /**
     * 评论数量
     */
    private Integer commentNum;
    /**
     * 是否推荐
     */
    private Boolean isRecommend;

    /**
     * 用户头像
     */
    @TableField(exist = false)
    private String avatar;
    /**
     * 用户昵称
     */
    @TableField(exist = false)
    private String nickname;
    /**
     * 话题名称
     */
    @TableField(exist = false)
    private String topicName;
    /**
     * 圈子名称
     */
    @TableField(exist = false)
    private String circleName;
    /**
     * 是否点赞
     */
    @TableField(exist = false)
    private Boolean isLiked;
    /**
     * 是否关注
     */
    @TableField(exist = false)
    private Boolean isFollowed;

    /**
     * 设置默认值
     */
    public void setDefaultValue() {
        // 点赞数量
        this.likeNum = 0;
        // 浏览数量
        this.browseNum = 0;
        // 评论数量
        this.commentNum = 0;
        // 默认推荐
        this.isRecommend = true;
    }

}
