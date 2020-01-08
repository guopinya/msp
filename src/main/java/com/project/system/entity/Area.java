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
    private String areaParentId;
    private String areaName;
    private String areaCode;
    private String areaAbbre;
    private String areaLevel;
    private String areaCitycode;
    private String areaZipcode;
    private String areaBaiducode;
    private String areaLng;
    private String areaLat;
    private String areaIsHot;
}
