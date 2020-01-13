package com.project.sq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.common.entity.BaseEnity;
import lombok.Data;

/**
 * 帖子达人
 *
 * @author zhuyifa
 */
@Data
@TableName(value = "sq_post_master")
public class PostMaster extends BaseEnity {

    /**
     * 帖子达人ID
     */
    @TableId
    private String id;
    /**
     * 帖子ID
     */
    private String postId;
    /**
     * 达人ID（用户ID）
     */
    private String masterId;
    /**
     * 操作类型 0浏览 1点赞 2评论
     */
    private String actType;
    /**
     * 评论内容
     */
    private String content;

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
     * 获取操作名称
     *
     * @return 操作名称
     */
    public String obtainActName() {
        // 浏览
        if ("0".equalsIgnoreCase(actType)) {
            return "浏览";
        }
        // 点赞
        if ("1".equalsIgnoreCase(actType)) {
            return "点赞";
        }
        // 评论
        if ("2".equalsIgnoreCase(actType)) {
            return "评论";
        }
        return "";
    }

}
