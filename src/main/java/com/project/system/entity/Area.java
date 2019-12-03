package com.project.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

import java.io.Serializable;

/**
 * 地区-gpy
 */
@Data
@TableName(value = "system_area")
public class Area extends BaseEnity implements Serializable {
    @TableId
    private String id;
    private String parentId;
    private String areaName;
    private String areaCode;
    private String abbre;
    private String level;
    private String citycode;
    private String zipcode;
    private String baiducode;
    private String lng;
    private String lat;
    private String isHot;
}
