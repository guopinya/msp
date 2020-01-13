package com.project.sq.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

/**
 * 消息
 *
 * @author zhuyifa
 */
@Data
@TableName(value = "sq_msg")
public class Msg extends BaseEnity {

    /**
     * 发送人ID
     */
    private String senderId;
    /**
     * 接收人ID
     */
    private String receiverId;
    /**
     * 关联的ID（众筹ID/帖子ID）
     */
    private String relationId;
    /**
     * 消息类型 0系统 1众筹 2点赞与评论 3新粉丝
     */
    private String type;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否已读
     */
    private Boolean isRead;

    public Msg(Follow p) {
        // 新粉丝消息
        this.type = "3";
        // 发送人ID
        this.senderId = p.getUserId();
        // 接收人ID
        this.receiverId = p.getTargetId();
    }

}
