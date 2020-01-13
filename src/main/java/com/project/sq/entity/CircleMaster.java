package com.project.sq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

import java.util.List;

/**
 * 圈子达人
 *
 * @author zhuyifa
 */
@Data
@TableName(value = "sq_circle_master")
public class CircleMaster extends BaseEnity {

    /**
     * 圈子达人ID
     */
    @TableId
    private String id;
    /**
     * 圈子ID
     */
    private String circleId;
    /**
     * 达人ID（用户ID）
     */
    private String masterId;

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
     * 是否获取帖子
     */
    @TableField(exist = false)
    private Boolean isGetPost;
    /**
     * 是否关注
     */
    @TableField(exist = false)
    private Boolean isFollowed;
    /**
     * 帖子列表
     */
    @TableField(exist = false)
    private List<Post> postList;

}
