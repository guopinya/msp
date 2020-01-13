package com.project.sq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

/**
 * 关注
 *
 * @author zhuyifa
 */
@Data
@TableName(value = "sq_follow")
public class Follow extends BaseEnity {

    /**
     * 帖子达人ID
     */
    @TableId
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 关注对象ID
     */
    private String targetId;

}
