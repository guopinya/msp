package com.project.msp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

import java.io.Serializable;

/**
 * 分类-gpy
 */
@Data
@TableName(value = "msp_attr")
public class Attr extends BaseEnity implements Serializable {
    @TableId
    private String id;
    private String areaId;
    private String attrParentId;
    private String attrName;
    private String attrCode;
    private String attrImage;
    private String attrDesc;
    private String attrType;
}
