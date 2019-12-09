package com.project.msp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.common.entity.BaseEnity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 评价-gpy
 */
@Data
@TableName(value = "msp_evaluate")
public class Evaluate extends BaseEnity implements Serializable {
    @TableId
    private String id;
    private String orderId;
    private String projectId;
    private String shopId;
    private String servicerId;
    private String userId;
    private String score;
    private String detail;
    private String banner;
    private String state;
    @TableField(exist = false)
    private String orderNo;
    @TableField(exist = false)
    private String projectName;
    @TableField(exist = false)
    private String shopName;
    @TableField(exist = false)
    private String servicername;
    @TableField(exist = false)
    private String username;
}
