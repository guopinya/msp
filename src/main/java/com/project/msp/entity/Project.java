package com.project.msp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 项目-gpy
 */
@Data
@TableName(value = "msp_project")
public class Project extends BaseEnity implements Serializable {
    @TableId
    private String id;
    private String areaId;
    private String attrId;
    private String projectName;
    private String projectNo;
    private String projectImage;
    private String projectBanner;
    private String projectTag;
    private BigDecimal projectBasePrice;
    private BigDecimal projectPrice;
    private String projectDetail;
}
