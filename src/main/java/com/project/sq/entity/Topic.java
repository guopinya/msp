package com.project.sq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

import java.util.List;

/**
 * 话题
 *
 * @author zhuyifa
 */
@Data
@TableName(value = "sq_topic")
public class Topic extends BaseEnity {

    public static final String DEFAULT_PARENT = "0";

    /**
     * 话题ID
     */
    @TableId
    private String id;
    /**
     * 父话题ID
     */
    private String parentId;
    /**
     * 话题名称
     */
    private String name;
    /**
     * 话题图片
     */
    private String image;
    /**
     * 帖子数量
     */
    private Integer postNum;

    /**
     * 子话题列表
     */
    @TableField(exist = false)
    private List<Topic> children;

    /**
     * 设置默认值
     */
    public void setDefaultValue() {
        // 帖子数量
        this.postNum = 0;
        // 父话题ID
        if (parentId == null
                || parentId.isEmpty()) {
            parentId = DEFAULT_PARENT;
        }
    }

}
