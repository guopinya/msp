package com.project.sq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

import java.util.List;

/**
 * 搜索
 *
 * @author zhuyifa
 */
@Data
@TableName(value = "sq_find")
public class Find extends BaseEnity {

    /**
     * 搜索ID
     */
    @TableId
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 搜索类型 0全部 1帖子 2圈子 3用户
     */
    private String type;
    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 操作名称 populars热门搜索 histories搜索历史
     */
    @TableField(exist = false)
    private String opName;

}
