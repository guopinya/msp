package com.project.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

import java.io.Serializable;

/**
 * 参数-gpy
 */
@Data
@TableName(value = "system_param")
public class Param extends BaseEnity implements Serializable {
    @TableId
    private String id;
    private String areaId;
    private String paramParentId;
    private String paramKey;
    private String paramValue;
    private String paramDesc;
    private String paramType;
}
