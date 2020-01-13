package com.project.sq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

/**
 * 圈子
 *
 * @author zhuyifa
 */
@Data
@TableName(value = "sq_circle")
public class Circle extends BaseEnity {

    /**
     * 圈子ID
     */
    @TableId
    private String id;
    /**
     * 圈子名称
     */
    private String name;
    /**
     * 圈子图片
     */
    private String image;
    /**
     * 圈子描述
     */
    private String depict;
    /**
     * 用户数量
     */
    private Integer userNum;
    /**
     * 帖子数量
     */
    private Integer postNum;


    /**
     * 达人ID（用户ID）
     */
    @TableField(exist = false)
    private String masterId;
    /**
     * 是否加入
     */
    @TableField(exist = false)
    private Boolean isJoined;

    /**
     * 设置默认值
     */
    public void setDefaultValue() {
        // 用户数量
        this.userNum = 0;
        // 帖子数量
        this.postNum = 0;
    }

}
