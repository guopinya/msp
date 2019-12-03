package com.project.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

import java.io.Serializable;

/**
 * 轮播图
 *
 * @author zhuyifa
 * @since 2019-08-08
 */
@Data
@TableName(value = "system_banner")
public class Banner extends BaseEnity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId
    private String id;
    private String areaId;
    private String areaName;

    /**
     * 图片
     */
    private String image;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型
     */
    private String type;

    /**
     * 对象ID
     */
    private String objId;

    /**
     * 链接
     */
    private String link;

    /**
     * 富文本
     */
    private String richText;

    /**
     * 显示顺序
     */
    private Integer sortNumber;
}
